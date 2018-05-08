package com.siziksu.crypto.data.mock;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.google.gson.Gson;
import com.siziksu.crypto.common.Constants;
import com.siziksu.crypto.common.utils.FileUtils;
import com.siziksu.crypto.data.client.model.CoinResponse;
import com.siziksu.crypto.data.client.model.CoinsResponse;
import com.siziksu.crypto.data.client.model.HistoricalResponse;
import com.siziksu.crypto.data.client.model.PortfolioResponse;
import com.siziksu.crypto.data.client.model.TradeRequest;
import com.siziksu.crypto.data.client.service.CryptoService;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.Single;

public class CryptoServiceMock implements CryptoService {

    private FileUtils fileUtils;

    public CryptoServiceMock() {
        fileUtils = new FileUtils();
    }

    @Override
    public Single<CoinsResponse> getCoins() {
        return Single.create(emitter -> {
            try {
                String json = getStringFromFile("response/get_coins.json");
                CoinsResponse coinsResponse = new Gson().fromJson(json, CoinsResponse.class);
                emitter.onSuccess(coinsResponse);
            } catch (Exception e) {
                Log.e(Constants.TAG, e.getMessage(), e);
                emitter.onError(e);
            }
        });
    }

    @Override
    public Single<CoinResponse> getCoin(int coinId) {
        return Single.create(emitter -> {
            try {
                String json = getStringFromFile("response/get_coin.json");
                CoinResponse coinResponse = new Gson().fromJson(json, CoinResponse.class);
                emitter.onSuccess(coinResponse);
            } catch (Exception e) {
                Log.e(Constants.TAG, e.getMessage(), e);
                emitter.onError(e);
            }
        });
    }

    @Override
    public Single<HistoricalResponse> getCoinHistorical(int coinId) {
        return Single.create(emitter -> {
            try {
                String json = getStringFromFile("response/get_coin_historical.json");
                HistoricalResponse coinResponse = new Gson().fromJson(json, HistoricalResponse.class);
                emitter.onSuccess(coinResponse);
            } catch (Exception e) {
                Log.e(Constants.TAG, e.getMessage(), e);
                emitter.onError(e);
            }
        });
    }

    @Override
    public Single<PortfolioResponse> getPortfolio(String authorization) {
        return Single.create(emitter -> {
            try {
                String json = getStringFromFile("response/get_portfolio.json");
                PortfolioResponse coinResponse = new Gson().fromJson(json, PortfolioResponse.class);
                emitter.onSuccess(coinResponse);
            } catch (Exception e) {
                Log.e(Constants.TAG, e.getMessage(), e);
                emitter.onError(e);
            }
        });
    }

    @Override
    public Completable addCoinToPortfolio(String authorization, TradeRequest tradeDataModel) {
        return Completable.create(CompletableEmitter::onComplete);
    }

    private String getStringFromFile(String filName) {
        Context context = InstrumentationRegistry.getTargetContext();
        return fileUtils.getFileContent(context, filName);
    }
}
