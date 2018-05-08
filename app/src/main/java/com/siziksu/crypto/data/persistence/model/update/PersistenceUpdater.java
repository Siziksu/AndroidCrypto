package com.siziksu.crypto.data.persistence.model.update;

import com.siziksu.crypto.data.model.CoinDataModel;
import com.siziksu.crypto.data.model.CoinsDataModel;
import com.siziksu.crypto.data.persistence.model.CoinEntity;
import com.siziksu.crypto.data.persistence.model.CoinsEntity;

import java.util.List;

import io.realm.RealmList;

public class PersistenceUpdater {

    private PersistenceUpdater() {}

    public static void updateCoinsEntityFromCoinsDataModel(CoinsEntity coinsEntity, CoinsDataModel coinsDataModel) {
        coinsEntity.total = coinsDataModel.total;
        coinsEntity.perPage = coinsDataModel.perPage;
        coinsEntity.currentPage = coinsDataModel.currentPage;
        coinsEntity.lastPage = coinsDataModel.lastPage;
        coinsEntity.firstPageUrl = coinsDataModel.firstPageUrl;
        coinsEntity.lastPageUrl = coinsDataModel.lastPageUrl;
        coinsEntity.nextPageUrl = coinsDataModel.nextPageUrl;
        coinsEntity.prevPageUrl = coinsDataModel.prevPageUrl;
        coinsEntity.path = coinsDataModel.path;
        coinsEntity.from = coinsDataModel.from;
        coinsEntity.to = coinsDataModel.to;
        updateListOfCoinEntityFromListOfCoinDataModel(coinsEntity.data, coinsDataModel.data);
    }

    private static void updateListOfCoinEntityFromListOfCoinDataModel(RealmList<CoinEntity> coinsEntity, List<CoinDataModel> coinsDataModel) {
        for (CoinDataModel coinDataModel : coinsDataModel) {
            coinsEntity.add(updateCoinFromCoinDataModel(coinDataModel));
        }
    }

    private static CoinEntity updateCoinFromCoinDataModel(CoinDataModel coinDataModel) {
        CoinEntity coinEntity = new CoinEntity();
        coinEntity.id = coinDataModel.id;
        coinEntity.name = coinDataModel.name;
        coinEntity.symbol = coinDataModel.symbol;
        coinEntity.logo = coinDataModel.logo;
        coinEntity.rank = coinDataModel.rank;
        coinEntity.priceUsd = coinDataModel.priceUsd;
        coinEntity.priceBtc = coinDataModel.priceBtc;
        coinEntity.volumeUsd24h = coinDataModel.volumeUsd24h;
        coinEntity.marketCapUsd = coinDataModel.marketCapUsd;
        coinEntity.availableSupply = coinDataModel.availableSupply;
        coinEntity.totalSupply = coinDataModel.totalSupply;
        coinEntity.percentChange1h = coinDataModel.percentChange1h;
        coinEntity.percentChange24h = coinDataModel.percentChange24h;
        coinEntity.percentChange7d = coinDataModel.percentChange7d;
        coinEntity.createdAt = coinDataModel.createdAt;
        coinEntity.updatedAt = coinDataModel.updatedAt;
        return coinEntity;
    }
}
