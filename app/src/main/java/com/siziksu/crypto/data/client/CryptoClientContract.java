package com.siziksu.crypto.data.client;

import com.siziksu.crypto.data.client.model.TradeRequest;
import com.siziksu.crypto.data.model.CoinDataModel;
import com.siziksu.crypto.data.model.CoinsDataModel;
import com.siziksu.crypto.data.model.HistoricalDataModel;
import com.siziksu.crypto.data.model.PortfolioDataModel;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface CryptoClientContract {

    /**
     * Gets a list of all cryptocurrencies from an API.
     *
     * @return list of each coin
     */
    Single<CoinsDataModel> getCoins();

    /**
     * Gets specific details about a cryptocurrency from an API.
     *
     * @param coinId the id of the coin
     *
     * @return an object with the details
     */
    Single<CoinDataModel> getCoin(int coinId);

    /**
     * Gets the historical data of a cryptocurrency from an API.
     *
     * @param coinId the id of the coin
     *
     * @return list of the historical data
     */
    Single<HistoricalDataModel> getCoinHistorical(int coinId);

    /**
     * Gets the user portfolio from an API.
     *
     * @param user     user part of the basic authorization credentials
     * @param password password part of the basic authorization credentials
     *
     * @return the user portfolio
     */
    Single<PortfolioDataModel> getPortfolio(String user, String password);

    /**
     * Adds a coin to the user portfolio.
     *
     * @param user         user part of the basic authorization credentials
     * @param password     password part of the basic authorization credentials
     * @param tradeRequest the trade coin data
     *
     * @return the portfolio coin
     */
    Completable addCoinToPortfolio(String user, String password, TradeRequest tradeRequest);
}
