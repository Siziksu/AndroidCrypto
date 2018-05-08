package com.siziksu.crypto.presenter.detail;

import android.util.Log;

import com.github.mikephil.charting.data.LineData;
import com.siziksu.crypto.R;
import com.siziksu.crypto.common.Constants;
import com.siziksu.crypto.common.managers.ThrowableManager;
import com.siziksu.crypto.common.managers.model.Info;
import com.siziksu.crypto.common.utils.DatesUtils;
import com.siziksu.crypto.data.RepositoryContract;
import com.siziksu.crypto.data.model.TradeDataModel;
import com.siziksu.crypto.presenter.mapper.CoinHistoricalMapper;
import com.siziksu.crypto.ui.common.DialogFragmentHelper;
import com.siziksu.crypto.ui.model.Coin;
import com.siziksu.crypto.ui.model.CoinHistorical;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenter implements DetailPresenterContract<DetailViewContract> {

    private static final long SECONDS_BEFORE_FOR_SERVER_NEEDS = 20000;

    @Inject
    RepositoryContract repository;

    private DetailViewContract view;
    private List<CoinHistorical> coinsHistorical = new ArrayList<>();
    private Disposable disposable;

    public DetailPresenter(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void register(DetailViewContract view) {
        this.view = view;
    }

    @Override
    public void unregister() {
        view = null;
        cancelLastRequest();
    }

    @Override
    public void start(int coinId) {
        if (view != null) {
            view.showLoadingDialog();
        }
        cancelLastRequest();
        disposable = repository.getCoinHistorical(coinId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        coinsHistoricalDataModel -> {
                            if (!coinsHistoricalDataModel.isEmpty()) {
                                coinsHistorical.clear();
                                coinsHistorical.addAll(new CoinHistoricalMapper().map(coinsHistoricalDataModel));
                                prepareLineChartAndSendDataToDraw();
                            }
                        },
                        this::showGetCoinHistoricalError
                );
    }

    @Override
    public void onCoinTradeClick(Coin coin) {
        if (view != null) {
            DialogFragmentHelper.showTradeDialog(view.getAppCompatActivity(), amount -> {
                if (amount != 0) {
                    trade(coin, amount);
                } else {
                    if (view != null) {
                        view.showMessage(view.getAppCompatActivity().getString(R.string.trade_amount_can_not_be_zero));
                    }
                }
            });
        }
    }

    private void trade(Coin coin, float amount) {
        TradeDataModel tradeDataModel = buildTradeRequest(coin, amount);
        disposable = repository.addCoinToPortfolio("richard@rich.com", "secret", tradeDataModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            if (view != null) {
                                view.showMessage(view.getAppCompatActivity().getString(R.string.trade_completed));
                            }
                        },
                        this::showAddCoinToPortfolioError
                );
    }

    private TradeDataModel buildTradeRequest(Coin coin, float amount) {
        TradeDataModel tradeDataModel = new TradeDataModel();
        tradeDataModel.coinId = coin.id;
        tradeDataModel.amount = amount;
        tradeDataModel.priceUsd = Float.valueOf(coin.priceUsd);
        tradeDataModel.tradedAt = DatesUtils.getDateFormatted(new Date().getTime() - SECONDS_BEFORE_FOR_SERVER_NEEDS);
        return tradeDataModel;
    }

    private void prepareLineChartAndSendDataToDraw() {
        String label = null;
        if (view != null) {
            label = view.getAppCompatActivity().getString(R.string.historical_data_of_the_price);
        }
        LineData data = new LineChartHelper().buildAndGetTheLineDataForTheLineChart(label, coinsHistorical);
        if (view != null) {
            view.drawDataInTheLineChart(data);
            view.hideLoadingDialog();
        }
    }

    private void showGetCoinHistoricalError(Throwable throwable) {
        Info error = ThrowableManager.handleException(throwable);
        String message;
        switch (error.id) {
            case ThrowableManager.PERSISTENCE_HISTORICAL_NULL:
            case ThrowableManager.UNKNOWN_HOST_EXCEPTION:
            case ThrowableManager.CONNECTION_EXCEPTION:
                message = ThrowableManager.CONNECTION_EXCEPTION_INFO.info;
                break;
            default:
                message = ThrowableManager.GENERIC_EXCEPTION_INFO.info;
                break;
        }
        if (view != null) {
            view.showMessage(message);
            view.hideLoadingDialog();
        }
    }

    private void showAddCoinToPortfolioError(Throwable throwable) {
        Log.e(Constants.TAG, throwable.getMessage(), throwable);
        Info error = ThrowableManager.handleException(throwable);
        String message;
        switch (error.id) {
            case ThrowableManager.BAD_REQUEST_EXCEPTION:
                message = ThrowableManager.BAD_REQUEST_EXCEPTION_INFO.info;
                break;
            case ThrowableManager.UNAUTHORIZED_EXCEPTION:
                message = ThrowableManager.UNAUTHORIZED_EXCEPTION_INFO.info;
                break;
            case ThrowableManager.UNKNOWN_HOST_EXCEPTION:
            case ThrowableManager.CONNECTION_EXCEPTION:
                message = ThrowableManager.CONNECTION_EXCEPTION_INFO.info;
                break;
            default:
                message = ThrowableManager.GENERIC_EXCEPTION_INFO.info;
                break;
        }
        if (view != null) {
            view.showMessage(message);
            view.hideLoadingDialog();
        }
    }

    private void cancelLastRequest() {
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }
}
