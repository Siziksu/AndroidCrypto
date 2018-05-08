package com.siziksu.crypto.data.mapper.persistence;

import com.siziksu.crypto.common.mapper.RealmMapper;
import com.siziksu.crypto.data.model.CoinDataModel;
import com.siziksu.crypto.data.model.CoinsDataModel;
import com.siziksu.crypto.data.persistence.model.CoinEntity;
import com.siziksu.crypto.data.persistence.model.CoinsEntity;

public class CoinMapper extends RealmMapper<CoinEntity, CoinDataModel> {

    @Override
    public CoinDataModel map(CoinEntity coinEntity) {
        CoinDataModel coinDataModel = new CoinDataModel();
        coinDataModel.id = coinEntity.id;
        coinDataModel.name = coinEntity.name;
        coinDataModel.symbol = coinEntity.symbol;
        coinDataModel.logo = coinEntity.logo;
        coinDataModel.rank = coinEntity.rank;
        coinDataModel.priceUsd = coinEntity.priceUsd;
        coinDataModel.priceBtc = coinEntity.priceBtc;
        coinDataModel.volumeUsd24h = coinEntity.volumeUsd24h;
        coinDataModel.marketCapUsd = coinEntity.marketCapUsd;
        coinDataModel.availableSupply = coinEntity.availableSupply;
        coinDataModel.totalSupply = coinEntity.totalSupply;
        coinDataModel.percentChange1h = coinEntity.percentChange1h;
        coinDataModel.percentChange24h = coinEntity.percentChange24h;
        coinDataModel.percentChange7d = coinEntity.percentChange7d;
        coinDataModel.createdAt = coinEntity.createdAt;
        coinDataModel.updatedAt = coinEntity.updatedAt;
        return coinDataModel;
    }

    @Override
    public CoinEntity unMap(CoinDataModel coinDataModel) {
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

    public CoinsDataModel map(CoinsEntity coinsEntity) {
        CoinsDataModel coinsDataModel = new CoinsDataModel();
        coinsDataModel.total = coinsEntity.total;
        coinsDataModel.perPage = coinsEntity.perPage;
        coinsDataModel.currentPage = coinsEntity.currentPage;
        coinsDataModel.lastPage = coinsEntity.lastPage;
        coinsDataModel.firstPageUrl = coinsEntity.firstPageUrl;
        coinsDataModel.lastPageUrl = coinsEntity.lastPageUrl;
        coinsDataModel.nextPageUrl = coinsEntity.nextPageUrl;
        coinsDataModel.prevPageUrl = coinsEntity.prevPageUrl;
        coinsDataModel.path = coinsEntity.path;
        coinsDataModel.from = coinsEntity.from;
        coinsDataModel.to = coinsEntity.to;
        coinsDataModel.data = map(coinsEntity.data);
        return coinsDataModel;
    }

    public CoinsEntity unMap(CoinsDataModel coinsDataModel) {
        CoinsEntity coinsEntity = new CoinsEntity();
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
        coinsEntity.data = unMap(coinsDataModel.data);
        return coinsEntity;
    }
}
