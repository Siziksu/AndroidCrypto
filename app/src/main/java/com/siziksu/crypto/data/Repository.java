package com.siziksu.crypto.data;

import android.content.Context;

import com.siziksu.crypto.common.managers.ConnectionManager;
import com.siziksu.crypto.data.client.CryptoClientContract;
import com.siziksu.crypto.data.mapper.client.CoinMapper;
import com.siziksu.crypto.data.mapper.client.HistoricalMapper;
import com.siziksu.crypto.data.mapper.client.PortfolioMapper;
import com.siziksu.crypto.data.model.CoinDataModel;
import com.siziksu.crypto.data.model.CoinHistoricalDataModel;
import com.siziksu.crypto.data.model.PortfolioCoinDataModel;
import com.siziksu.crypto.data.model.TradeDataModel;
import com.siziksu.crypto.data.persistence.PersistenceClientContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class Repository implements RepositoryContract {

    @Inject
    Context context;
    @Inject
    ConnectionManager connectionManager;
    @Inject
    PersistenceClientContract persistenceClient;
    @Inject
    CryptoClientContract cryptoClient;

    public Repository(Context context,
                      ConnectionManager connectionManager,
                      PersistenceClientContract persistenceClient,
                      CryptoClientContract cryptoClient) {
        this.context = context;
        this.connectionManager = connectionManager;
        this.persistenceClient = persistenceClient;
        this.cryptoClient = cryptoClient;
    }

    @Override
    public Single<List<CoinDataModel>> getCoins() {
        if (!connectionManager.getConnection().isConnected()) {
            return persistenceClient.getCoins()
                    .map(new CoinMapper()::map);
        } else {
            return cryptoClient.getCoins()
                    .doOnSuccess(coinsDataModel -> persistenceClient.addCoins(coinsDataModel))
                    .map(new CoinMapper()::map);
        }
    }

    @Override
    public Single<CoinDataModel> getCoin(int coinId) {
        if (!connectionManager.getConnection().isConnected()) {
            return persistenceClient.getCoin(coinId);
        } else {
            return cryptoClient.getCoin(coinId);
        }
    }

    @Override
    public Single<List<CoinHistoricalDataModel>> getCoinHistorical(int coinId) {
        if (!connectionManager.getConnection().isConnected()) {
            return persistenceClient.getCoinHistorical(coinId)
                    .map(new HistoricalMapper()::map);
        } else {
            return cryptoClient.getCoinHistorical(coinId)
                    .doOnSuccess(historicalDataModel -> persistenceClient.addHistorical(coinId, historicalDataModel))
                    .map(new HistoricalMapper()::map);
        }
    }

    @Override
    public Single<List<PortfolioCoinDataModel>> getPortfolio(String user, String password) {
        if (!connectionManager.getConnection().isConnected()) {
            return persistenceClient.getPortfolio()
                    .map(new PortfolioMapper()::map);
        } else {
            return cryptoClient.getPortfolio(user, password)
                    .doOnSuccess(portfolioDataModel -> persistenceClient.addPortfolio(portfolioDataModel))
                    .map(new PortfolioMapper()::map);
        }
    }

    @Override
    public Completable addCoinToPortfolio(String user, String password, TradeDataModel tradeDataModel) {
        return cryptoClient.addCoinToPortfolio(user, password, new PortfolioMapper().map(tradeDataModel));
    }
}
