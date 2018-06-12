package com.siziksu.crypto.domain.portfolio;

import com.siziksu.crypto.domain.model.PortfolioCoinDomainModel;

import java.util.List;

public interface PortfolioDomainPresenterContract {

    void showLoadingDialog();

    void hideLoadingDialog();

    void showMessage(String message);

    void showPortfolioCoinList(List<PortfolioCoinDomainModel> portfolioCoins);
}
