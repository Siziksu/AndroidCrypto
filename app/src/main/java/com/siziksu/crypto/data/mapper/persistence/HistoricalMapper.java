package com.siziksu.crypto.data.mapper.persistence;

import com.siziksu.crypto.common.mapper.RealmMapper;
import com.siziksu.crypto.data.model.CoinHistoricalDataModel;
import com.siziksu.crypto.data.model.HistoricalDataModel;
import com.siziksu.crypto.data.persistence.model.CoinHistoricalEntity;
import com.siziksu.crypto.data.persistence.model.HistoricalEntity;

public class HistoricalMapper extends RealmMapper<CoinHistoricalEntity, CoinHistoricalDataModel> {

    @Override
    public CoinHistoricalDataModel map(CoinHistoricalEntity coinHistoricalEntity) {
        CoinHistoricalDataModel coinHistoricalDataModel = new CoinHistoricalDataModel();
        coinHistoricalDataModel.priceUsd = coinHistoricalEntity.priceUsd;
        coinHistoricalDataModel.snapshotAt = coinHistoricalEntity.snapshotAt;
        return coinHistoricalDataModel;
    }

    @Override
    public CoinHistoricalEntity unMap(CoinHistoricalDataModel coinHistoricalDataModel) {
        CoinHistoricalEntity coinHistoricalEntity = new CoinHistoricalEntity();
        coinHistoricalEntity.priceUsd = coinHistoricalDataModel.priceUsd;
        coinHistoricalEntity.snapshotAt = coinHistoricalDataModel.snapshotAt;
        return coinHistoricalEntity;
    }

    public HistoricalDataModel map(HistoricalEntity historicalEntity) {
        HistoricalDataModel historicalDataModel = new HistoricalDataModel();
        historicalDataModel.historical.addAll(new HistoricalMapper().map(historicalEntity.historical));
        return historicalDataModel;
    }

    public HistoricalEntity unMap(int coinId, HistoricalDataModel historicalDataModel) {
        HistoricalEntity historicalEntity = new HistoricalEntity();
        historicalEntity.id = coinId;
        historicalEntity.historical.addAll(new HistoricalMapper().unMap(historicalDataModel.historical));
        return historicalEntity;
    }
}
