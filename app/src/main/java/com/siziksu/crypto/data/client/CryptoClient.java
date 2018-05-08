package com.siziksu.crypto.data.client;

import com.siziksu.crypto.data.client.model.TradeRequest;
import com.siziksu.crypto.data.client.service.CryptoService;
import com.siziksu.crypto.data.client.service.RetrofitServiceFactoryContract;
import com.siziksu.crypto.data.mapper.client.CoinMapper;
import com.siziksu.crypto.data.mapper.client.HistoricalMapper;
import com.siziksu.crypto.data.mapper.client.PortfolioMapper;
import com.siziksu.crypto.data.model.CoinDataModel;
import com.siziksu.crypto.data.model.CoinsDataModel;
import com.siziksu.crypto.data.model.HistoricalDataModel;
import com.siziksu.crypto.data.model.PortfolioDataModel;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import okhttp3.Credentials;

public class CryptoClient implements CryptoClientContract {

    private static final String URI = "https://test.cryptojet.io";

    @Inject
    RetrofitServiceFactoryContract retrofitServiceFactory;

    private CryptoService service;

    public CryptoClient(RetrofitServiceFactoryContract retrofitServiceFactory) {
        this.retrofitServiceFactory = retrofitServiceFactory;
        service = this.retrofitServiceFactory.createService(CryptoService.class, URI);
    }

    public CryptoClient(CryptoService service) {
        this.service = service;
    }

    @Override
    public Single<CoinsDataModel> getCoins() {
        return service.getCoins().map(new CoinMapper()::map);
    }

    @Override
    public Single<CoinDataModel> getCoin(int coinId) {
        return service.getCoin(coinId).map(new CoinMapper()::map);
    }

    @Override
    public Single<HistoricalDataModel> getCoinHistorical(int coinId) {
        return service.getCoinHistorical(coinId).map(new HistoricalMapper()::map);
    }

    @Override
    public Single<PortfolioDataModel> getPortfolio(String user, String password) {
        String credentials = Credentials.basic(user, password);
        return service.getPortfolio(credentials).map(new PortfolioMapper()::map);
    }

    @Override
    public Completable addCoinToPortfolio(String user, String password, TradeRequest tradeRequest) {
        String credentials = Credentials.basic(user, password);
        return service.addCoinToPortfolio(credentials, tradeRequest);
    }
}
