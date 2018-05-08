package com.siziksu.crypto.data.mapper.client;

import com.siziksu.crypto.common.mapper.Mapper;
import com.siziksu.crypto.data.client.model.CoinHistoricalClientModel;
import com.siziksu.crypto.data.client.model.HistoricalResponse;
import com.siziksu.crypto.data.model.CoinHistoricalDataModel;
import com.siziksu.crypto.data.model.HistoricalDataModel;

import java.util.List;

public class HistoricalMapper extends Mapper<CoinHistoricalClientModel, CoinHistoricalDataModel> {

    @Override
    public CoinHistoricalDataModel map(CoinHistoricalClientModel coinHistoricalClientModel) {
        CoinHistoricalDataModel coinHistoricalDataModel = new CoinHistoricalDataModel();
        coinHistoricalDataModel.priceUsd = coinHistoricalClientModel.priceUsd;
        coinHistoricalDataModel.snapshotAt = coinHistoricalClientModel.snapshotAt;
        return coinHistoricalDataModel;
    }

    @Override
    public CoinHistoricalClientModel unMap(CoinHistoricalDataModel coinHistoricalDataModel) {
        CoinHistoricalClientModel coinHistoricalClientModel = new CoinHistoricalClientModel();
        coinHistoricalClientModel.priceUsd = coinHistoricalDataModel.priceUsd;
        coinHistoricalClientModel.snapshotAt = coinHistoricalDataModel.snapshotAt;
        return coinHistoricalClientModel;
    }

    public HistoricalDataModel map(HistoricalResponse historicalResponse) {
        HistoricalDataModel historicalDataModel = new HistoricalDataModel();
        historicalDataModel.historical.addAll(map(historicalResponse.historical));
        return historicalDataModel;
    }

    public List<CoinHistoricalDataModel> map(HistoricalDataModel historicalDataModel) {
        return historicalDataModel.historical;
    }
}
