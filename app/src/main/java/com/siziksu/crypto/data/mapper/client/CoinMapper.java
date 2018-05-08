package com.siziksu.crypto.data.mapper.client;

import com.siziksu.crypto.common.mapper.Mapper;
import com.siziksu.crypto.data.client.model.CoinClientModel;
import com.siziksu.crypto.data.client.model.CoinResponse;
import com.siziksu.crypto.data.client.model.CoinsResponse;
import com.siziksu.crypto.data.model.CoinDataModel;
import com.siziksu.crypto.data.model.CoinsDataModel;

import java.util.List;

public class CoinMapper extends Mapper<CoinClientModel, CoinDataModel> {

    @Override
    public CoinDataModel map(CoinClientModel coinClientModel) {
        CoinDataModel coinDataModel = new CoinDataModel();
        coinDataModel.id = coinClientModel.id;
        coinDataModel.name = coinClientModel.name;
        coinDataModel.symbol = coinClientModel.symbol;
        coinDataModel.logo = coinClientModel.logo;
        coinDataModel.rank = coinClientModel.rank;
        coinDataModel.priceUsd = coinClientModel.priceUsd;
        coinDataModel.priceBtc = coinClientModel.priceBtc;
        coinDataModel.volumeUsd24h = coinClientModel.volumeUsd24h;
        coinDataModel.marketCapUsd = coinClientModel.marketCapUsd;
        coinDataModel.availableSupply = coinClientModel.availableSupply;
        coinDataModel.totalSupply = coinClientModel.totalSupply;
        coinDataModel.percentChange1h = coinClientModel.percentChange1h;
        coinDataModel.percentChange24h = coinClientModel.percentChange24h;
        coinDataModel.percentChange7d = coinClientModel.percentChange7d;
        coinDataModel.createdAt = coinClientModel.createdAt;
        coinDataModel.updatedAt = coinClientModel.updatedAt;
        return coinDataModel;
    }

    @Override
    public CoinClientModel unMap(CoinDataModel coinDataModel) {
        CoinClientModel coinClientModel = new CoinClientModel();
        coinClientModel.id = coinDataModel.id;
        coinClientModel.name = coinDataModel.name;
        coinClientModel.symbol = coinDataModel.symbol;
        coinClientModel.logo = coinDataModel.logo;
        coinClientModel.rank = coinDataModel.rank;
        coinClientModel.priceUsd = coinDataModel.priceUsd;
        coinClientModel.priceBtc = coinDataModel.priceBtc;
        coinClientModel.volumeUsd24h = coinDataModel.volumeUsd24h;
        coinClientModel.marketCapUsd = coinDataModel.marketCapUsd;
        coinClientModel.availableSupply = coinDataModel.availableSupply;
        coinClientModel.totalSupply = coinDataModel.totalSupply;
        coinClientModel.percentChange1h = coinDataModel.percentChange1h;
        coinClientModel.percentChange24h = coinDataModel.percentChange24h;
        coinClientModel.percentChange7d = coinDataModel.percentChange7d;
        coinClientModel.createdAt = coinDataModel.createdAt;
        coinClientModel.updatedAt = coinDataModel.updatedAt;
        return coinClientModel;
    }

    public CoinDataModel map(CoinResponse coinResponse) {
        return map(coinResponse.coin);
    }

    public List<CoinDataModel> map(CoinsDataModel coinsDataModel) {
        return coinsDataModel.data;
    }

    public CoinsDataModel map(CoinsResponse coinsResponse) {
        CoinsDataModel list = new CoinsDataModel();
        list.total = coinsResponse.coins.total;
        list.perPage = coinsResponse.coins.perPage;
        list.currentPage = coinsResponse.coins.currentPage;
        list.lastPage = coinsResponse.coins.lastPage;
        list.firstPageUrl = coinsResponse.coins.firstPageUrl;
        list.lastPageUrl = coinsResponse.coins.lastPageUrl;
        list.nextPageUrl = coinsResponse.coins.nextPageUrl;
        list.prevPageUrl = coinsResponse.coins.prevPageUrl;
        list.path = coinsResponse.coins.path;
        list.from = coinsResponse.coins.from;
        list.to = coinsResponse.coins.to;
        list.data = map(coinsResponse.coins.data);
        return list;
    }
}
