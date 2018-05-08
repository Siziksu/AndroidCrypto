package com.siziksu.crypto.presenter.mapper;

import com.siziksu.crypto.common.mapper.Mapper;
import com.siziksu.crypto.data.model.CoinHistoricalDataModel;
import com.siziksu.crypto.ui.model.CoinHistorical;

public class CoinHistoricalMapper extends Mapper<CoinHistoricalDataModel, CoinHistorical> {

    @Override
    public CoinHistorical map(CoinHistoricalDataModel coinHistoricalDataModel) {
        CoinHistorical coinHistorical = new CoinHistorical();
        coinHistorical.priceUsd = coinHistoricalDataModel.priceUsd;
        coinHistorical.snapshotAt = coinHistoricalDataModel.snapshotAt;
        return coinHistorical;
    }

    @Override
    public CoinHistoricalDataModel unMap(CoinHistorical coinHistorical) {
        CoinHistoricalDataModel coinHistoricalDataModel = new CoinHistoricalDataModel();
        coinHistoricalDataModel.priceUsd = coinHistorical.priceUsd;
        coinHistoricalDataModel.snapshotAt = coinHistorical.snapshotAt;
        return coinHistoricalDataModel;
    }
}
