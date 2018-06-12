package com.siziksu.crypto.ui.view.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.LineData;
import com.siziksu.crypto.App;
import com.siziksu.crypto.R;
import com.siziksu.crypto.common.Constants;
import com.siziksu.crypto.common.utils.MathUtils;
import com.siziksu.crypto.presenter.detail.DetailPresenterContract;
import com.siziksu.crypto.presenter.detail.DetailViewContract;
import com.siziksu.crypto.ui.common.DialogFragmentHelper;
import com.siziksu.crypto.ui.common.SnackbarHelper;
import com.siziksu.crypto.presenter.model.Coin;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailActivity extends AppCompatActivity implements DetailViewContract {

    @Inject
    DetailPresenterContract<DetailViewContract> presenter;

    @BindView(R.id.coinDetailName)
    TextView coinName;
    @BindView(R.id.coinDetailPriceUsd)
    TextView coinPriceUsd;
    @BindView(R.id.coinDetailPercentChange24h)
    TextView coinPercentChange24h;
    @BindView(R.id.coinDetailRank)
    TextView coinRank;
    @BindView(R.id.coinDetailPriceBtc)
    TextView coinPriceBtc;
    @BindView(R.id.coinDetailVolumeUsd24h)
    TextView coinVolume24h;
    @BindView(R.id.coinDetailMarketCap)
    TextView coinMarketCap;
    @BindView(R.id.lineChart)
    LineChart lineChart;

    private Coin coin;
    private boolean alreadyStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.get().getApplicationComponent().inject(this);
        setContentView(R.layout.activity_detail);
        checkIntentExtras();
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
            if (coin != null) {
                presenter.start(coin.id);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unregister();
    }

    @OnClick(R.id.coinDetailTrade)
    public void onCoinTradeClick() {
        presenter.onCoinTradeClick(coin);
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
    public void drawDataInTheLineChart(LineData data) {
        lineChart.setData(data);
        lineChart.invalidate();
    }

    @Override
    public void showMessage(String message) {
        SnackbarHelper.showInfiniteSnackbar(coinName, message);
    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        return this;
    }

    private void checkIntentExtras() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(Constants.EXTRAS_COIN)) {
            coin = bundle.getParcelable(Constants.EXTRAS_COIN);
        }
    }

    private void initializeViews() {
        ButterKnife.bind(this);
        lineChart.setDragEnabled(true);
        lineChart.setScaleXEnabled(true);
        lineChart.setScaleYEnabled(false);
        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description);
        if (coin != null) {
            coinName.setText(String.format(getString(R.string.name_value), coin.name, coin.symbol));
            coinPriceUsd.setText(String.format(getString(R.string.price_usd_value), MathUtils.roundAndFormatNumber(coin.priceUsd)));
            int color = MathUtils.isPositive(coin.percentChange24h) ? R.color.positiveChange : R.color.negativeChange;
            coinPercentChange24h.setTextColor(ContextCompat.getColor(this, color));
            coinPercentChange24h.setText(String.format(getString(R.string.change_24h_value), MathUtils.roundAndFormatNumber(coin.percentChange24h)));
            coinRank.setText(String.valueOf(coin.rank));
            color = MathUtils.isPositive(coin.priceBtc) ? R.color.positiveChange : R.color.negativeChange;
            coinPriceBtc.setTextColor(ContextCompat.getColor(this, color));
            coinPriceBtc.setText(String.format(getString(R.string.price_usd_value), MathUtils.roundAndFormatNumber(coin.priceBtc)));
            coinVolume24h.setText(String.format(getString(R.string.price_usd_value), MathUtils.roundAndFormatNumber(String.valueOf(coin.volumeUsd24h))));
            coinMarketCap.setText(String.format(getString(R.string.price_usd_value), MathUtils.roundAndFormatNumber(String.valueOf(coin.marketCapUsd))));
        }
    }
}
