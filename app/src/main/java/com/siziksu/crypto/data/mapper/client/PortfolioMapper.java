package com.siziksu.crypto.data.mapper.client;

import com.siziksu.crypto.common.mapper.Mapper;
import com.siziksu.crypto.data.client.model.PortfolioCoinClientModel;
import com.siziksu.crypto.data.client.model.PortfolioResponse;
import com.siziksu.crypto.data.client.model.PortfolioTradeResponse;
import com.siziksu.crypto.data.client.model.TradeRequest;
import com.siziksu.crypto.data.model.PortfolioCoinDataModel;
import com.siziksu.crypto.data.model.PortfolioDataModel;
import com.siziksu.crypto.data.model.TradeDataModel;

import java.util.List;

public class PortfolioMapper extends Mapper<PortfolioCoinClientModel, PortfolioCoinDataModel> {

    @Override
    public PortfolioCoinDataModel map(PortfolioCoinClientModel portfolioCoinClientModel) {
        PortfolioCoinDataModel portfolioCoinDataModel = new PortfolioCoinDataModel();
        portfolioCoinDataModel.coinId = portfolioCoinClientModel.coinId;
        portfolioCoinDataModel.amount = portfolioCoinClientModel.amount;
        portfolioCoinDataModel.priceUsd = portfolioCoinClientModel.priceUsd;
        portfolioCoinDataModel.userId = portfolioCoinClientModel.userId;
        portfolioCoinDataModel.totalUsd = portfolioCoinClientModel.totalUsd;
        portfolioCoinDataModel.notes = portfolioCoinClientModel.notes;
        portfolioCoinDataModel.id = portfolioCoinClientModel.id;
        portfolioCoinDataModel.createdAt = portfolioCoinClientModel.createdAt;
        portfolioCoinDataModel.updatedAt = portfolioCoinClientModel.updatedAt;
        portfolioCoinDataModel.tradedAt = portfolioCoinClientModel.tradedAt;
        return portfolioCoinDataModel;
    }

    @Override
    public PortfolioCoinClientModel unMap(PortfolioCoinDataModel portfolioCoinDataModel) {
        PortfolioCoinClientModel portfolioCoinClientModel = new PortfolioCoinClientModel();
        portfolioCoinClientModel.coinId = portfolioCoinDataModel.coinId;
        portfolioCoinClientModel.amount = portfolioCoinDataModel.amount;
        portfolioCoinClientModel.priceUsd = portfolioCoinDataModel.priceUsd;
        portfolioCoinClientModel.userId = portfolioCoinDataModel.userId;
        portfolioCoinClientModel.totalUsd = portfolioCoinDataModel.totalUsd;
        portfolioCoinClientModel.notes = portfolioCoinDataModel.notes;
        portfolioCoinClientModel.id = portfolioCoinDataModel.id;
        portfolioCoinClientModel.createdAt = portfolioCoinDataModel.createdAt;
        portfolioCoinClientModel.updatedAt = portfolioCoinDataModel.updatedAt;
        portfolioCoinClientModel.tradedAt = portfolioCoinDataModel.tradedAt;
        return portfolioCoinClientModel;
    }

    public PortfolioDataModel map(PortfolioResponse portfolioResponse) {
        PortfolioDataModel portfolioDataModel = new PortfolioDataModel();
        portfolioDataModel.coins.addAll(map(portfolioResponse.coins));
        return portfolioDataModel;
    }

    public List<PortfolioCoinDataModel> map(PortfolioDataModel portfolioDataModel) {
        return portfolioDataModel.coins;
    }

    public PortfolioCoinDataModel map(PortfolioTradeResponse portfolioTradeResponse) {
        return map(portfolioTradeResponse.trade);
    }

    public TradeRequest map(TradeDataModel tradeDataModel) {
        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.coinId = tradeDataModel.coinId;
        tradeRequest.amount = tradeDataModel.amount;
        tradeRequest.priceUsd = tradeDataModel.priceUsd;
        tradeRequest.notes = tradeDataModel.notes;
        tradeRequest.tradedAt = tradeDataModel.tradedAt;
        return tradeRequest;
    }
}
