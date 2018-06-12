package com.siziksu.crypto.domain.mapper;

import com.siziksu.crypto.common.mapper.Mapper;
import com.siziksu.crypto.data.model.PortfolioCoinDataModel;
import com.siziksu.crypto.domain.model.PortfolioCoinDomainModel;

public class PortfolioCoinMapper extends Mapper<PortfolioCoinDataModel, PortfolioCoinDomainModel> {

    @Override
    public PortfolioCoinDomainModel map(PortfolioCoinDataModel object) {
        PortfolioCoinDomainModel portfolioCoinDomainModel = new PortfolioCoinDomainModel();
        portfolioCoinDomainModel.coinId = object.coinId;
        portfolioCoinDomainModel.amount = object.amount;
        portfolioCoinDomainModel.priceUsd = object.priceUsd;
        portfolioCoinDomainModel.userId = object.userId;
        portfolioCoinDomainModel.totalUsd = object.totalUsd;
        portfolioCoinDomainModel.notes = object.notes;
        portfolioCoinDomainModel.id = object.id;
        portfolioCoinDomainModel.createdAt = object.createdAt;
        portfolioCoinDomainModel.updatedAt = object.updatedAt;
        portfolioCoinDomainModel.tradedAt = object.tradedAt;
        return portfolioCoinDomainModel;
    }

    @Override
    public PortfolioCoinDataModel unMap(PortfolioCoinDomainModel mapped) {
        PortfolioCoinDataModel portfolioCoinDataModel = new PortfolioCoinDataModel();
        portfolioCoinDataModel.coinId = mapped.coinId;
        portfolioCoinDataModel.amount = mapped.amount;
        portfolioCoinDataModel.priceUsd = mapped.priceUsd;
        portfolioCoinDataModel.userId = mapped.userId;
        portfolioCoinDataModel.totalUsd = mapped.totalUsd;
        portfolioCoinDataModel.notes = mapped.notes;
        portfolioCoinDataModel.id = mapped.id;
        portfolioCoinDataModel.createdAt = mapped.createdAt;
        portfolioCoinDataModel.updatedAt = mapped.updatedAt;
        portfolioCoinDataModel.tradedAt = mapped.tradedAt;
        return portfolioCoinDataModel;
    }
}
