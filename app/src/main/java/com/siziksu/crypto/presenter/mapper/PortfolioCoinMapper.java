package com.siziksu.crypto.presenter.mapper;

import com.siziksu.crypto.common.mapper.Mapper;
import com.siziksu.crypto.data.model.PortfolioCoinDataModel;
import com.siziksu.crypto.ui.model.PortfolioCoin;

public class PortfolioCoinMapper extends Mapper<PortfolioCoinDataModel, PortfolioCoin> {

    @Override
    public PortfolioCoin map(PortfolioCoinDataModel portfolioCoinDataModel) {
        PortfolioCoin portfolioCoin = new PortfolioCoin();
        portfolioCoin.coinId = portfolioCoinDataModel.coinId;
        portfolioCoin.amount = portfolioCoinDataModel.amount;
        portfolioCoin.priceUsd = portfolioCoinDataModel.priceUsd;
        portfolioCoin.userId = portfolioCoinDataModel.userId;
        portfolioCoin.totalUsd = portfolioCoinDataModel.totalUsd;
        portfolioCoin.notes = portfolioCoinDataModel.notes;
        portfolioCoin.id = portfolioCoinDataModel.id;
        portfolioCoin.createdAt = portfolioCoinDataModel.createdAt;
        portfolioCoin.updatedAt = portfolioCoinDataModel.updatedAt;
        portfolioCoin.tradedAt = portfolioCoinDataModel.tradedAt;
        return portfolioCoin;
    }

    @Override
    public PortfolioCoinDataModel unMap(PortfolioCoin portfolioCoin) {
        PortfolioCoinDataModel portfolioCoinDataModel = new PortfolioCoinDataModel();
        portfolioCoinDataModel.coinId = portfolioCoin.coinId;
        portfolioCoinDataModel.amount = portfolioCoin.amount;
        portfolioCoinDataModel.priceUsd = portfolioCoin.priceUsd;
        portfolioCoinDataModel.userId = portfolioCoin.userId;
        portfolioCoinDataModel.totalUsd = portfolioCoin.totalUsd;
        portfolioCoinDataModel.notes = portfolioCoin.notes;
        portfolioCoinDataModel.id = portfolioCoin.id;
        portfolioCoinDataModel.createdAt = portfolioCoin.createdAt;
        portfolioCoinDataModel.updatedAt = portfolioCoin.updatedAt;
        portfolioCoinDataModel.tradedAt = portfolioCoin.tradedAt;
        return portfolioCoinDataModel;
    }
}
