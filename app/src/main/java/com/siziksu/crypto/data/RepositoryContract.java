package com.siziksu.crypto.data;

import com.siziksu.crypto.data.model.TradeDataModel;
import com.siziksu.crypto.data.model.CoinDataModel;
import com.siziksu.crypto.data.model.CoinHistoricalDataModel;
import com.siziksu.crypto.data.model.PortfolioCoinDataModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface RepositoryContract {

    /**
     * Gets a list of all cryptocurrencies.
     *
     * @return list of each coin
     */
    Single<List<CoinDataModel>> getCoins();

    /**
     * Gets specific details about a cryptocurrency.
     *
     * @param coinId the id of the coin
     *
     * @return an object with the details
     */
    Single<CoinDataModel> getCoin(int coinId);

    /**
     * Gets the historical data of a cryptocurrency.
     *
     * @param coinId the id of the coin
     *
     * @return list of the historical data
     */
    Single<List<CoinHistoricalDataModel>> getCoinHistorical(int coinId);

    /**
     * Gets the user portfolio.
     *
     * @param user     user part of the basic authorization credentials
     * @param password password part of the basic authorization credentials
     *
     * @return the user portfolio
     */
    Single<List<PortfolioCoinDataModel>> getPortfolio(String user, String password);

    /**
     * Adds a coin to the user portfolio.
     *
     * @param user         user part of the basic authorization credentials
     * @param password     password part of the basic authorization credentials
     * @param tradeDataModel the trade coin data
     *
     * @return the portfolio coin
     */
    Completable addCoinToPortfolio(String user, String password, TradeDataModel tradeDataModel);
}
