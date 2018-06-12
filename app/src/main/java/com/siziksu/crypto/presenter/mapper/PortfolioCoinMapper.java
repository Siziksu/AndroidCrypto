package com.siziksu.crypto.presenter.mapper;

import com.siziksu.crypto.common.mapper.Mapper;
import com.siziksu.crypto.domain.model.PortfolioCoinDomainModel;
import com.siziksu.crypto.presenter.model.PortfolioCoin;

public class PortfolioCoinMapper extends Mapper<PortfolioCoinDomainModel, PortfolioCoin> {

    @Override
    public PortfolioCoin map(PortfolioCoinDomainModel object) {
        PortfolioCoin portfolioCoin = new PortfolioCoin();
        portfolioCoin.coinId = object.coinId;
        portfolioCoin.amount = object.amount;
        portfolioCoin.priceUsd = object.priceUsd;
        portfolioCoin.userId = object.userId;
        portfolioCoin.totalUsd = object.totalUsd;
        portfolioCoin.notes = object.notes;
        portfolioCoin.id = object.id;
        portfolioCoin.createdAt = object.createdAt;
        portfolioCoin.updatedAt = object.updatedAt;
        portfolioCoin.tradedAt = object.tradedAt;

        portfolioCoin.name = object.name;
        portfolioCoin.symbol = object.symbol;
        portfolioCoin.price = object.price;
        return portfolioCoin;
    }

    @Override
    public PortfolioCoinDomainModel unMap(PortfolioCoin mapped) {
        PortfolioCoinDomainModel portfolioCoinDomainModel = new PortfolioCoinDomainModel();
        portfolioCoinDomainModel.coinId = mapped.coinId;
        portfolioCoinDomainModel.amount = mapped.amount;
        portfolioCoinDomainModel.priceUsd = mapped.priceUsd;
        portfolioCoinDomainModel.userId = mapped.userId;
        portfolioCoinDomainModel.totalUsd = mapped.totalUsd;
        portfolioCoinDomainModel.notes = mapped.notes;
        portfolioCoinDomainModel.id = mapped.id;
        portfolioCoinDomainModel.createdAt = mapped.createdAt;
        portfolioCoinDomainModel.updatedAt = mapped.updatedAt;
        portfolioCoinDomainModel.tradedAt = mapped.tradedAt;

        portfolioCoinDomainModel.name = mapped.name;
        portfolioCoinDomainModel.symbol = mapped.symbol;
        portfolioCoinDomainModel.price = mapped.price;
        return portfolioCoinDomainModel;
    }
}
