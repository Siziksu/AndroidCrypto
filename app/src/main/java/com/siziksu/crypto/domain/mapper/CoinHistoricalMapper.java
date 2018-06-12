package com.siziksu.crypto.domain.mapper;

import com.siziksu.crypto.common.mapper.Mapper;
import com.siziksu.crypto.data.model.CoinHistoricalDataModel;
import com.siziksu.crypto.domain.model.CoinHistoricalDomainModel;

public class CoinHistoricalMapper extends Mapper<CoinHistoricalDataModel, CoinHistoricalDomainModel> {

    @Override
    public CoinHistoricalDomainModel map(CoinHistoricalDataModel object) {
        CoinHistoricalDomainModel coinHistoricalDomainModel = new CoinHistoricalDomainModel();
        coinHistoricalDomainModel.priceUsd = object.priceUsd;
        coinHistoricalDomainModel.snapshotAt = object.snapshotAt;
        return coinHistoricalDomainModel;
    }

    @Override
    public CoinHistoricalDataModel unMap(CoinHistoricalDomainModel mapped) {
        CoinHistoricalDataModel coinHistoricalDataModel = new CoinHistoricalDataModel();
        coinHistoricalDataModel.priceUsd = mapped.priceUsd;
        coinHistoricalDataModel.snapshotAt = mapped.snapshotAt;
        return coinHistoricalDataModel;
    }
}
