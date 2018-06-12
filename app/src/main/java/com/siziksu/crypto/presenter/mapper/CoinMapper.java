package com.siziksu.crypto.presenter.mapper;

import com.siziksu.crypto.common.mapper.Mapper;
import com.siziksu.crypto.domain.model.CoinDomainModel;
import com.siziksu.crypto.presenter.model.Coin;

public class CoinMapper extends Mapper<CoinDomainModel, Coin> {

    @Override
    public Coin map(CoinDomainModel object) {
        Coin coin = new Coin();
        coin.id = object.id;
        coin.name = object.name;
        coin.symbol = object.symbol;
        coin.logo = object.logo;
        coin.rank = object.rank;
        coin.priceUsd = object.priceUsd;
        coin.priceBtc = object.priceBtc;
        coin.volumeUsd24h = object.volumeUsd24h;
        coin.marketCapUsd = object.marketCapUsd;
        coin.availableSupply = object.availableSupply;
        coin.totalSupply = object.totalSupply;
        coin.percentChange1h = object.percentChange1h;
        coin.percentChange24h = object.percentChange24h;
        coin.percentChange7d = object.percentChange7d;
        coin.createdAt = object.createdAt;
        coin.updatedAt = object.updatedAt;
        return coin;
    }

    @Override
    public CoinDomainModel unMap(Coin mapped) {
        CoinDomainModel coinDomainModel = new CoinDomainModel();
        coinDomainModel.id = mapped.id;
        coinDomainModel.name = mapped.name;
        coinDomainModel.symbol = mapped.symbol;
        coinDomainModel.logo = mapped.logo;
        coinDomainModel.rank = mapped.rank;
        coinDomainModel.priceUsd = mapped.priceUsd;
        coinDomainModel.priceBtc = mapped.priceBtc;
        coinDomainModel.volumeUsd24h = mapped.volumeUsd24h;
        coinDomainModel.marketCapUsd = mapped.marketCapUsd;
        coinDomainModel.availableSupply = mapped.availableSupply;
        coinDomainModel.totalSupply = mapped.totalSupply;
        coinDomainModel.percentChange1h = mapped.percentChange1h;
        coinDomainModel.percentChange24h = mapped.percentChange24h;
        coinDomainModel.percentChange7d = mapped.percentChange7d;
        coinDomainModel.createdAt = mapped.createdAt;
        coinDomainModel.updatedAt = mapped.updatedAt;
        return coinDomainModel;
    }
}
