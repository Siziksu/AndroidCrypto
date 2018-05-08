package com.siziksu.crypto.data.persistence;

import com.siziksu.crypto.data.model.CoinDataModel;
import com.siziksu.crypto.data.model.CoinsDataModel;
import com.siziksu.crypto.data.model.HistoricalDataModel;
import com.siziksu.crypto.data.model.PortfolioDataModel;

import io.reactivex.Single;

public interface PersistenceClientContract {

    /**
     * Gets a list of all cryptocurrencies from a database.
     *
     * @return list of each coin
     */
    Single<CoinsDataModel> getCoins();

    /**
     * Gets specific details about a cryptocurrency from a database.
     *
     * @param coinId the id of the coin
     *
     * @return an object with the details
     */
    Single<CoinDataModel> getCoin(int coinId);

    /**
     * Gets the historical data of a cryptocurrency from a database.
     *
     * @param coinId the id of the coin
     *
     * @return list of the historical data
     */
    Single<HistoricalDataModel> getCoinHistorical(int coinId);

    /**
     * Gets the user portfolio from a database.
     *
     * @return the user portfolio
     */
    Single<PortfolioDataModel> getPortfolio();

    /**
     * Adds the coins to a database.
     *
     * @param coinsDataModel the object to add
     */
    void addCoins(CoinsDataModel coinsDataModel);

    /**
     * Adds the historical data of a coin to a database.
     *
     * @param coinId              the id of the coin
     * @param historicalDataModel the data to add
     */
    void addHistorical(int coinId, HistoricalDataModel historicalDataModel);

    /**
     * Adds the portfolio to a database.
     *
     * @param portfolioDataModel the data to add
     */
    void addPortfolio(PortfolioDataModel portfolioDataModel);
}
