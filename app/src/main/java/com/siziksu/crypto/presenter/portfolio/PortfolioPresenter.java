package com.siziksu.crypto.presenter.portfolio;

import com.siziksu.crypto.domain.model.PortfolioCoinDomainModel;
import com.siziksu.crypto.domain.portfolio.PortfolioDomainContract;
import com.siziksu.crypto.domain.portfolio.PortfolioDomainPresenterContract;
import com.siziksu.crypto.presenter.mapper.PortfolioCoinMapper;
import com.siziksu.crypto.presenter.model.PortfolioCoin;

import java.util.List;

import javax.inject.Inject;

public class PortfolioPresenter implements PortfolioPresenterContract<PortfolioViewContract>, PortfolioDomainPresenterContract {

    @Inject
    PortfolioDomainContract<PortfolioDomainPresenterContract> domain;

    private PortfolioViewContract view;

    public PortfolioPresenter(PortfolioDomainContract<PortfolioDomainPresenterContract> domain) {
        this.domain = domain;
    }

    @Override
    public void register(PortfolioViewContract view) {
        this.view = view;
        domain.register(this);
    }

    @Override
    public void unregister() {
        view = null;
        domain.unregister();
    }

    @Override
    public void start() {
        domain.start();
    }

    @Override
    public void showLoadingDialog() {
        if (view != null) {
            view.showLoadingDialog();
        }
    }

    @Override
    public void hideLoadingDialog() {
        if (view != null) {
            view.hideLoadingDialog();
        }
    }

    @Override
    public void showMessage(String message) {
        if (view != null) {
            view.showMessage(message);
        }
    }

    @Override
    public void showPortfolioCoinList(List<PortfolioCoinDomainModel> portfolioCoins) {
        if (view != null) {
            List<PortfolioCoin> list = new PortfolioCoinMapper().map(portfolioCoins);
            view.showPortfolioCoinList(list);
        }
    }
}
