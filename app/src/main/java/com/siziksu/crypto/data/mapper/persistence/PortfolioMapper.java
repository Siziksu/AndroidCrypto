package com.siziksu.crypto.data.mapper.persistence;

import com.siziksu.crypto.common.mapper.RealmMapper;
import com.siziksu.crypto.data.model.PortfolioCoinDataModel;
import com.siziksu.crypto.data.model.PortfolioDataModel;
import com.siziksu.crypto.data.persistence.model.PortfolioCoinEntity;
import com.siziksu.crypto.data.persistence.model.PortfolioEntity;

public class PortfolioMapper extends RealmMapper<PortfolioCoinEntity, PortfolioCoinDataModel> {

    @Override
    public PortfolioCoinDataModel map(PortfolioCoinEntity portfolioCoinEntity) {
        PortfolioCoinDataModel portfolioCoinDataModel = new PortfolioCoinDataModel();
        portfolioCoinDataModel.coinId = portfolioCoinEntity.coinId;
        portfolioCoinDataModel.amount = portfolioCoinEntity.amount;
        portfolioCoinDataModel.priceUsd = portfolioCoinEntity.priceUsd;
        return portfolioCoinDataModel;
    }

    @Override
    public PortfolioCoinEntity unMap(PortfolioCoinDataModel portfolioCoinDataModel) {
        PortfolioCoinEntity portfolioCoinEntity = new PortfolioCoinEntity();
        portfolioCoinEntity.coinId = portfolioCoinDataModel.coinId;
        portfolioCoinEntity.amount = portfolioCoinDataModel.amount;
        portfolioCoinEntity.priceUsd = portfolioCoinDataModel.priceUsd;
        return portfolioCoinEntity;
    }

    public PortfolioDataModel map(PortfolioEntity portfolioEntity) {
        PortfolioDataModel object = new PortfolioDataModel();
        object.coins.addAll(new PortfolioMapper().map(portfolioEntity.coins));
        return object;
    }

    public PortfolioEntity unMap(int userId, PortfolioDataModel portfolioDataModel) {
        PortfolioEntity portfolioEntity = new PortfolioEntity();
        portfolioEntity.id = userId;
        portfolioEntity.coins.addAll(unMap(portfolioDataModel.coins));
        return portfolioEntity;
    }
}
