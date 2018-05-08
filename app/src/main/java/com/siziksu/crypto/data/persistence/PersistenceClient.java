package com.siziksu.crypto.data.persistence;

import android.util.Log;

import com.siziksu.crypto.App;
import com.siziksu.crypto.common.Constants;
import com.siziksu.crypto.common.managers.model.PersistenceClientThrowable;
import com.siziksu.crypto.data.mapper.persistence.CoinMapper;
import com.siziksu.crypto.data.mapper.persistence.HistoricalMapper;
import com.siziksu.crypto.data.mapper.persistence.PortfolioMapper;
import com.siziksu.crypto.data.model.CoinDataModel;
import com.siziksu.crypto.data.model.CoinsDataModel;
import com.siziksu.crypto.data.model.HistoricalDataModel;
import com.siziksu.crypto.data.model.PortfolioDataModel;
import com.siziksu.crypto.data.persistence.model.CoinEntity;
import com.siziksu.crypto.data.persistence.model.CoinsEntity;
import com.siziksu.crypto.data.persistence.model.HistoricalEntity;
import com.siziksu.crypto.data.persistence.model.PortfolioEntity;
import com.siziksu.crypto.data.persistence.model.update.PersistenceUpdater;

import io.reactivex.Single;
import io.realm.Realm;

public class PersistenceClient implements PersistenceClientContract {

    private static final String FIELD_ID = "id";

    @Override
    public Single<CoinsDataModel> getCoins() {
        return Single.create(emitter -> {
            Realm realm = Realm.getInstance(App.get().getRealmConfiguration());
            try {
                CoinsEntity coinsEntity = realm.where(CoinsEntity.class).findFirst();
                if (coinsEntity != null) {
                    emitter.onSuccess(new CoinMapper().map(coinsEntity));
                } else {
                    emitter.onError(new PersistenceClientThrowable(PersistenceClientThrowable.COINS_NULL));
                }
            } catch (Exception e) {
                emitter.onError(e);
                Log.e(Constants.TAG, "Realm error: " + e.getMessage(), e);
            } finally {
                realm.close();
            }
        });
    }

    @Override
    public Single<CoinDataModel> getCoin(int coinId) {
        return Single.create(emitter -> {
            Realm realm = Realm.getInstance(App.get().getRealmConfiguration());
            try {
                CoinEntity coinEntity = realm.where(CoinEntity.class).equalTo(FIELD_ID, coinId).findFirst();
                if (coinEntity != null) {
                    emitter.onSuccess(new CoinMapper().map(coinEntity));
                } else {
                    emitter.onError(new PersistenceClientThrowable(PersistenceClientThrowable.COIN_NULL));
                }
            } catch (Exception e) {
                emitter.onError(e);
                Log.e(Constants.TAG, "Realm error: " + e.getMessage(), e);
            } finally {
                realm.close();
            }
        });
    }

    @Override
    public Single<HistoricalDataModel> getCoinHistorical(int coinId) {
        return Single.create(emitter -> {
            Realm realm = Realm.getInstance(App.get().getRealmConfiguration());
            try {
                HistoricalEntity historicalEntity = realm.where(HistoricalEntity.class).equalTo(FIELD_ID, coinId).findFirst();
                if (historicalEntity != null) {
                    emitter.onSuccess(new HistoricalMapper().map(historicalEntity));
                } else {
                    emitter.onError(new PersistenceClientThrowable(PersistenceClientThrowable.HISTORICAL_NULL));
                }
            } catch (Exception e) {
                emitter.onError(e);
                Log.e(Constants.TAG, "Realm error: " + e.getMessage(), e);
            } finally {
                realm.close();
            }
        });
    }

    @Override
    public Single<PortfolioDataModel> getPortfolio() {
        return Single.create(emitter -> {
            Realm realm = Realm.getInstance(App.get().getRealmConfiguration());
            try {
                int userId = 1;
                PortfolioEntity portfolioEntity = realm.where(PortfolioEntity.class).equalTo(FIELD_ID, userId).findFirst();
                if (portfolioEntity != null) {
                    emitter.onSuccess(new PortfolioMapper().map(portfolioEntity));
                } else {
                    emitter.onError(new PersistenceClientThrowable(PersistenceClientThrowable.PORTFOLIO_NULL));
                }
            } catch (Exception e) {
                emitter.onError(e);
                Log.e(Constants.TAG, "Realm error: " + e.getMessage(), e);
            } finally {
                realm.close();
            }
        });
    }

    @Override
    public void addCoins(CoinsDataModel coinsDataModel) {
        Realm realm = Realm.getInstance(App.get().getRealmConfiguration());
        try {
            CoinsEntity coinsEntity = realm.where(CoinsEntity.class).findFirst();
            realm.beginTransaction();
            if (coinsEntity != null) {
                PersistenceUpdater.updateCoinsEntityFromCoinsDataModel(coinsEntity, coinsDataModel);
            } else {
                realm.copyToRealm(new CoinMapper().unMap(coinsDataModel));
            }
            realm.commitTransaction();
        } catch (Exception e) {
            if (realm.isInTransaction()) {
                realm.cancelTransaction();
            }
            Log.e(Constants.TAG, "Realm error: " + e.getMessage(), e);
        } finally {
            realm.close();
        }
    }

    @Override
    public void addHistorical(int coinId, HistoricalDataModel historicalDataModel) {
        HistoricalEntity newHistoricalEntity = new HistoricalMapper().unMap(coinId, historicalDataModel);
        Realm realm = Realm.getInstance(App.get().getRealmConfiguration());
        try {
            HistoricalEntity historicalEntity = realm.where(HistoricalEntity.class).equalTo(FIELD_ID, coinId).findFirst();
            realm.beginTransaction();
            if (historicalEntity != null) {
                realm.copyToRealmOrUpdate(newHistoricalEntity);
            } else {
                realm.copyToRealm(newHistoricalEntity);
            }
            realm.commitTransaction();
        } catch (Exception e) {
            if (realm.isInTransaction()) {
                realm.cancelTransaction();
            }
            Log.e(Constants.TAG, "Realm error: " + e.getMessage(), e);
        } finally {
            realm.close();
        }
    }

    @Override
    public void addPortfolio(PortfolioDataModel portfolioDataModel) {
        int userId = 1;
        PortfolioEntity newPortfolioEntity = new PortfolioMapper().unMap(userId, portfolioDataModel);
        Realm realm = Realm.getInstance(App.get().getRealmConfiguration());
        try {
            PortfolioEntity portfolioEntity = realm.where(PortfolioEntity.class).equalTo(FIELD_ID, userId).findFirst();
            realm.beginTransaction();
            if (portfolioEntity != null) {
                realm.copyToRealmOrUpdate(newPortfolioEntity);
            } else {
                realm.copyToRealm(newPortfolioEntity);
            }
            realm.commitTransaction();
        } catch (Exception e) {
            if (realm.isInTransaction()) {
                realm.cancelTransaction();
            }
            Log.e(Constants.TAG, "Realm error: " + e.getMessage(), e);
        } finally {
            realm.close();
        }
    }
}
