package com.siziksu.crypto.ui.view.portfolio;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.siziksu.crypto.App;
import com.siziksu.crypto.R;
import com.siziksu.crypto.presenter.portfolio.PortfolioPresenterContract;
import com.siziksu.crypto.presenter.portfolio.PortfolioViewContract;
import com.siziksu.crypto.ui.common.DialogFragmentHelper;
import com.siziksu.crypto.ui.common.SnackbarHelper;
import com.siziksu.crypto.presenter.model.PortfolioCoin;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PortfolioActivity extends AppCompatActivity implements PortfolioViewContract {

    @Inject
    PortfolioAdapterContract adapter;
    @Inject
    PortfolioPresenterContract<PortfolioViewContract> presenter;

    @BindView(R.id.portfolioRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.portfolioNoResultsTextView)
    TextView noResults;

    private boolean alreadyStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        App.get().getApplicationComponent().inject(this);
        setContentView(R.layout.activity_portfolio);
        initializeViews();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
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
    public void showPortfolioCoinList(List<PortfolioCoin> list) {
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
        SnackbarHelper.showInfiniteSnackbar(recyclerView, message);
    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        return this;
    }

    private void initializeViews() {
        ButterKnife.bind(this);
        adapter = new PortfolioAdapter(this, new PortfolioManager());
        adapter.init();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter.getAdapter());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(adapter.getLayoutManager());
    }
}
