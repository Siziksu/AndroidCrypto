package com.siziksu.crypto.data.mock;

import android.util.Log;

import com.siziksu.crypto.common.Constants;
import com.siziksu.crypto.data.client.service.CryptoService;
import com.siziksu.crypto.data.mapper.client.CoinMapper;
import com.siziksu.crypto.data.mapper.client.HistoricalMapper;
import com.siziksu.crypto.data.mapper.client.PortfolioMapper;
import com.siziksu.crypto.data.model.CoinDataModel;
import com.siziksu.crypto.data.model.CoinsDataModel;
import com.siziksu.crypto.data.model.HistoricalDataModel;
import com.siziksu.crypto.data.model.PortfolioDataModel;
import com.siziksu.crypto.data.persistence.PersistenceClientContract;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class PersistenceClientMock implements PersistenceClientContract {

    private CryptoService cryptoServiceMock;

    public PersistenceClientMock() {
        cryptoServiceMock = new CryptoServiceMock();
    }

    @Override
    public Single<CoinsDataModel> getCoins() {
        return Single.create(emitter -> {
            try {
                cryptoServiceMock.getCoins()
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(
                                coinsResponse -> emitter.onSuccess(new CoinMapper().map(coinsResponse)),
                                emitter::onError
                        );
            } catch (Exception e) {
                Log.e(Constants.TAG, e.getMessage(), e);
                emitter.onError(e);
            }
        });
    }

    @Override
    public Single<CoinDataModel> getCoin(int coinId) {
        return Single.create(emitter -> {
            try {
                cryptoServiceMock.getCoin(1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(
                                coinResponse -> emitter.onSuccess(new CoinMapper().map(coinResponse)),
                                emitter::onError
                        );
            } catch (Exception e) {
                Log.e(Constants.TAG, e.getMessage(), e);
                emitter.onError(e);
            }
        });
    }

    @Override
    public Single<HistoricalDataModel> getCoinHistorical(int coinId) {
        return Single.create(emitter -> {
            try {
                cryptoServiceMock.getCoinHistorical(1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(
                                historicalResponse -> emitter.onSuccess(new HistoricalMapper().map(historicalResponse)),
                                emitter::onError
                        );
            } catch (Exception e) {
                Log.e(Constants.TAG, e.getMessage(), e);
                emitter.onError(e);
            }
        });
    }

    @Override
    public Single<PortfolioDataModel> getPortfolio() {
        return Single.create(emitter -> {
            try {
                cryptoServiceMock.getPortfolio("")
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(
                                portfolioResponse -> emitter.onSuccess(new PortfolioMapper().map(portfolioResponse)),
                                emitter::onError
                        );
            } catch (Exception e) {
                Log.e(Constants.TAG, e.getMessage(), e);
                emitter.onError(e);
            }
        });
    }

    @Override
    public void addCoins(CoinsDataModel coinsDataModel) {

    }

    @Override
    public void addHistorical(int coinId, HistoricalDataModel historicalDataModel) {

    }

    @Override
    public void addPortfolio(PortfolioDataModel portfolioDataModel) {

    }

}
