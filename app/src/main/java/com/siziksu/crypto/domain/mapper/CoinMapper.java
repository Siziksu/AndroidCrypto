package com.siziksu.crypto.domain.mapper;

import com.siziksu.crypto.common.mapper.Mapper;
import com.siziksu.crypto.data.model.CoinDataModel;
import com.siziksu.crypto.domain.model.CoinDomainModel;

public class CoinMapper extends Mapper<CoinDataModel, CoinDomainModel> {

    @Override
    public CoinDomainModel map(CoinDataModel object) {
        CoinDomainModel coinDomainModel = new CoinDomainModel();
        coinDomainModel.id = object.id;
        coinDomainModel.name = object.name;
        coinDomainModel.symbol = object.symbol;
        coinDomainModel.logo = object.logo;
        coinDomainModel.rank = object.rank;
        coinDomainModel.priceUsd = object.priceUsd;
        coinDomainModel.priceBtc = object.priceBtc;
        coinDomainModel.volumeUsd24h = object.volumeUsd24h;
        coinDomainModel.marketCapUsd = object.marketCapUsd;
        coinDomainModel.availableSupply = object.availableSupply;
        coinDomainModel.totalSupply = object.totalSupply;
        coinDomainModel.percentChange1h = object.percentChange1h;
        coinDomainModel.percentChange24h = object.percentChange24h;
        coinDomainModel.percentChange7d = object.percentChange7d;
        coinDomainModel.createdAt = object.createdAt;
        coinDomainModel.updatedAt = object.updatedAt;
        return coinDomainModel;
    }

    @Override
    public CoinDataModel unMap(CoinDomainModel mapped) {
        CoinDataModel coinDataModel = new CoinDataModel();
        coinDataModel.id = mapped.id;
        coinDataModel.name = mapped.name;
        coinDataModel.symbol = mapped.symbol;
        coinDataModel.logo = mapped.logo;
        coinDataModel.rank = mapped.rank;
        coinDataModel.priceUsd = mapped.priceUsd;
        coinDataModel.priceBtc = mapped.priceBtc;
        coinDataModel.volumeUsd24h = mapped.volumeUsd24h;
        coinDataModel.marketCapUsd = mapped.marketCapUsd;
        coinDataModel.availableSupply = mapped.availableSupply;
        coinDataModel.totalSupply = mapped.totalSupply;
        coinDataModel.percentChange1h = mapped.percentChange1h;
        coinDataModel.percentChange24h = mapped.percentChange24h;
        coinDataModel.percentChange7d = mapped.percentChange7d;
        coinDataModel.createdAt = mapped.createdAt;
        coinDataModel.updatedAt = mapped.updatedAt;
        return coinDataModel;
    }
}
