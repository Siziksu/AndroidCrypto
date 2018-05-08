package com.siziksu.crypto.presenter.mapper;

import com.siziksu.crypto.common.mapper.Mapper;
import com.siziksu.crypto.data.model.CoinDataModel;
import com.siziksu.crypto.ui.model.Coin;

public class CoinMapper extends Mapper<CoinDataModel, Coin> {

    @Override
    public Coin map(CoinDataModel coinDataModel) {
        Coin coin = new Coin();
        coin.id = coinDataModel.id;
        coin.name = coinDataModel.name;
        coin.symbol = coinDataModel.symbol;
        coin.logo = coinDataModel.logo;
        coin.rank = coinDataModel.rank;
        coin.priceUsd = coinDataModel.priceUsd;
        coin.priceBtc = coinDataModel.priceBtc;
        coin.volumeUsd24h = coinDataModel.volumeUsd24h;
        coin.marketCapUsd = coinDataModel.marketCapUsd;
        coin.availableSupply = coinDataModel.availableSupply;
        coin.totalSupply = coinDataModel.totalSupply;
        coin.percentChange1h = coinDataModel.percentChange1h;
        coin.percentChange24h = coinDataModel.percentChange24h;
        coin.percentChange7d = coinDataModel.percentChange7d;
        coin.createdAt = coinDataModel.createdAt;
        coin.updatedAt = coinDataModel.updatedAt;
        return coin;
    }

    @Override
    public CoinDataModel unMap(Coin coin) {
        CoinDataModel coinDataModel = new CoinDataModel();
        coinDataModel.id = coin.id;
        coinDataModel.name = coin.name;
        coinDataModel.symbol = coin.symbol;
        coinDataModel.logo = coin.logo;
        coinDataModel.rank = coin.rank;
        coinDataModel.priceUsd = coin.priceUsd;
        coinDataModel.priceBtc = coin.priceBtc;
        coinDataModel.volumeUsd24h = coin.volumeUsd24h;
        coinDataModel.marketCapUsd = coin.marketCapUsd;
        coinDataModel.availableSupply = coin.availableSupply;
        coinDataModel.totalSupply = coin.totalSupply;
        coinDataModel.percentChange1h = coin.percentChange1h;
        coinDataModel.percentChange24h = coin.percentChange24h;
        coinDataModel.percentChange7d = coin.percentChange7d;
        coinDataModel.createdAt = coin.createdAt;
        coinDataModel.updatedAt = coin.updatedAt;
        return coinDataModel;
    }
}
