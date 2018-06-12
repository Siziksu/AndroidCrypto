package com.siziksu.crypto.domain.detail;

import android.util.Log;

import com.github.mikephil.charting.data.LineData;
import com.siziksu.crypto.R;
import com.siziksu.crypto.common.Constants;
import com.siziksu.crypto.common.managers.ThrowableManager;
import com.siziksu.crypto.common.managers.model.Info;
import com.siziksu.crypto.common.utils.DatesUtils;
import com.siziksu.crypto.data.RepositoryContract;
import com.siziksu.crypto.data.model.TradeDataModel;
import com.siziksu.crypto.domain.mapper.CoinHistoricalMapper;
import com.siziksu.crypto.domain.model.CoinDomainModel;
import com.siziksu.crypto.domain.model.CoinHistoricalDomainModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public final class DetailDomain implements DetailDomainContract<DetailDomainPresenterContract> {

    private static final long SECONDS_BEFORE_FOR_SERVER_NEEDS = 20000;

    @Inject
    RepositoryContract repository;

    private Disposable disposable;
    private DetailDomainPresenterContract presenter;
    private List<CoinHistoricalDomainModel> coinsHistorical = new ArrayList<>();

    public DetailDomain(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void register(DetailDomainPresenterContract presenter) {
        this.presenter = presenter;
    }

    @Override
    public void unregister() {
        presenter = null;
        clearDisposable();
    }

    @Override
    public void start(int coinId) {
        if (presenter != null) {
            presenter.showLoadingDialog();
        }
        clearDisposable();
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
    public void trade(CoinDomainModel coin, float amount) {
        TradeDataModel tradeDataModel = buildTradeRequest(coin, amount);
        disposable = repository.addCoinToPortfolio(Constants.CREDENTIALS[0], Constants.CREDENTIALS[1], tradeDataModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            if (presenter != null) {
                                presenter.showMessage(presenter.getString(R.string.trade_completed));
                            }
                        },
                        this::showAddCoinToPortfolioError
                );
    }

    private TradeDataModel buildTradeRequest(CoinDomainModel coin, float amount) {
        TradeDataModel tradeDataModel = new TradeDataModel();
        tradeDataModel.coinId = coin.id;
        tradeDataModel.amount = amount;
        tradeDataModel.priceUsd = Float.valueOf(coin.priceUsd);
        tradeDataModel.tradedAt = DatesUtils.getDateFormatted(new Date().getTime() - SECONDS_BEFORE_FOR_SERVER_NEEDS);
        return tradeDataModel;
    }

    private void prepareLineChartAndSendDataToDraw() {
        String label = null;
        if (presenter != null) {
            label = presenter.getString(R.string.historical_data_of_the_price);
        }
        LineData data = new LineChartHelper().buildAndGetTheLineDataForTheLineChart(label, coinsHistorical);
        if (presenter != null) {
            presenter.drawDataInTheLineChart(data);
            presenter.hideLoadingDialog();
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
        if (presenter != null) {
            presenter.showMessage(message);
            presenter.hideLoadingDialog();
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
        if (presenter != null) {
            presenter.showMessage(message);
            presenter.hideLoadingDialog();
        }
    }

    private void clearDisposable() {
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }
}
