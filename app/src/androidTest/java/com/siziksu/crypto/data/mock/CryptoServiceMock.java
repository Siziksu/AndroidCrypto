package com.siziksu.crypto.data.mock;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.google.gson.Gson;
import com.siziksu.crypto.common.Constants;
import com.siziksu.crypto.data.client.model.CoinResponse;
import com.siziksu.crypto.data.client.model.CoinsResponse;
import com.siziksu.crypto.data.client.model.HistoricalResponse;
import com.siziksu.crypto.data.client.model.PortfolioResponse;
import com.siziksu.crypto.data.client.model.TradeRequest;
import com.siziksu.crypto.data.client.service.CryptoService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.reactivex.Completable;
import io.reactivex.Single;

public class CryptoServiceMock implements CryptoService {

    @Override
    public Single<CoinsResponse> getCoins() {
        return Single.create(emitter -> {
            BufferedReader reader = null;
            try {
                StringBuilder json = new StringBuilder();
                Context appContext = InstrumentationRegistry.getTargetContext();
                reader = new BufferedReader(new InputStreamReader(appContext.getAssets().open("response/get_coins.json")));
                String line;
                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }
                CoinsResponse coinsResponse = new Gson().fromJson(json.toString(), CoinsResponse.class);
                emitter.onSuccess(coinsResponse);
            } catch (Exception e) {
                Log.e(Constants.TAG, e.getMessage(), e);
                emitter.onError(e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(Constants.TAG, e.getMessage(), e);
                    }
                }
            }
        });
    }

    @Override
    public Single<CoinResponse> getCoin(int coinId) {
        return Single.create(emitter -> {
            BufferedReader reader = null;
            try {
                StringBuilder json = new StringBuilder();
                Context appContext = InstrumentationRegistry.getTargetContext();
                reader = new BufferedReader(new InputStreamReader(appContext.getAssets().open("response/get_coin.json")));
                String line;
                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }
                CoinResponse coinResponse = new Gson().fromJson(json.toString(), CoinResponse.class);
                emitter.onSuccess(coinResponse);
            } catch (Exception e) {
                Log.e(Constants.TAG, e.getMessage(), e);
                emitter.onError(e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(Constants.TAG, e.getMessage(), e);
                    }
                }
            }
        });
    }

    @Override
    public Single<HistoricalResponse> getCoinHistorical(int coinId) {
        return Single.create(emitter -> {
            BufferedReader reader = null;
            try {
                StringBuilder json = new StringBuilder();
                Context appContext = InstrumentationRegistry.getTargetContext();
                reader = new BufferedReader(new InputStreamReader(appContext.getAssets().open("response/get_coin_historical.json")));
                String line;
                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }
                HistoricalResponse coinResponse = new Gson().fromJson(json.toString(), HistoricalResponse.class);
                emitter.onSuccess(coinResponse);
            } catch (Exception e) {
                Log.e(Constants.TAG, e.getMessage(), e);
                emitter.onError(e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(Constants.TAG, e.getMessage(), e);
                    }
                }
            }
        });
    }

    @Override
    public Single<PortfolioResponse> getPortfolio(String authorization) {
        return Single.create(emitter -> {
            BufferedReader reader = null;
            try {
                StringBuilder json = new StringBuilder();
                Context appContext = InstrumentationRegistry.getTargetContext();
                reader = new BufferedReader(new InputStreamReader(appContext.getAssets().open("response/get_portfolio.json")));
                String line;
                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }
                PortfolioResponse coinResponse = new Gson().fromJson(json.toString(), PortfolioResponse.class);
                emitter.onSuccess(coinResponse);
            } catch (Exception e) {
                Log.e(Constants.TAG, e.getMessage(), e);
                emitter.onError(e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(Constants.TAG, e.getMessage(), e);
                    }
                }
            }
        });
    }

    @Override
    public Completable addCoinToPortfolio(String authorization, TradeRequest tradeDataModel) {
        return null;
    }
}
