package com.siziksu.crypto.data.client.service;

import com.siziksu.crypto.data.client.model.CoinResponse;
import com.siziksu.crypto.data.client.model.CoinsResponse;
import com.siziksu.crypto.data.client.model.HistoricalResponse;
import com.siziksu.crypto.data.client.model.PortfolioResponse;
import com.siziksu.crypto.data.client.model.TradeRequest;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CryptoService {

    @GET("/coins")
    Single<CoinsResponse> getCoins();

    @GET("/coins/{coin_id}")
    Single<CoinResponse> getCoin(@Path("coin_id") int coinId);

    @GET("/coins/{coin_id}/historical")
    Single<HistoricalResponse> getCoinHistorical(@Path("coin_id") int coinId);

    @GET("/portfolio")
    Single<PortfolioResponse> getPortfolio(@Header("Authorization") String authorization);

    @POST("/portfolio")
    Completable addCoinToPortfolio(@Header("Authorization") String authorization, @Body TradeRequest tradeDataModel);
}
