package com.siziksu.crypto.ui.view.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.siziksu.crypto.App;
import com.siziksu.crypto.R;
import com.siziksu.crypto.presenter.main.MainPresenterContract;
import com.siziksu.crypto.presenter.main.MainViewContract;
import com.siziksu.crypto.ui.common.DialogFragmentHelper;
import com.siziksu.crypto.ui.common.SnackbarHelper;
import com.siziksu.crypto.ui.model.Coin;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements MainViewContract {

    @Inject
    CoinsAdapterContract adapter;
    @Inject
    MainPresenterContract<MainViewContract> presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mainRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.mainNoResultsTextView)
    TextView noResults;

    private boolean alreadyStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.get().getApplicationComponent().inject(this);
        setContentView(R.layout.activity_main);
        initializeViews();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_portfolio:
                presenter.onMenuPortfolioClick();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.register(this);
        if (!alreadyStarted) {
            alreadyStarted = true;
            presenter.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unregister();
    }

    @Override
    public void showLoadingDialog() {
        DialogFragmentHelper.showLoadingDialog(this);
    }

    @Override
    public void hideLoadingDialog() {
        DialogFragmentHelper.hideLoadingDialog(this);
    }

    @Override
    public void showCoinList(List<Coin> list) {
        if (list.isEmpty()) {
            noResults.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            noResults.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter.showItems(list);
        }
    }

    @Override
    public void showMessage(String message) {
        SnackbarHelper.showInfiniteSnackbar(toolbar, message);
    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        return this;
    }

    private void initializeViews() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        adapter = new CoinsAdapter(this, new CoinsManager());
        adapter.init(
                (v, position) -> {
                    Coin coin = adapter.getItem(position);
                    switch (v.getId()) {
                        case R.id.coinCardView:
                            presenter.onCoinClick(coin);
                            break;
                        default:
                            break;
                    }
                }
        );
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter.getAdapter());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(adapter.getLayoutManager());
    }
}
