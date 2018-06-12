package com.siziksu.crypto.ui.view.portfolio;

import com.siziksu.crypto.presenter.model.PortfolioCoin;

import java.util.List;

public interface PortfolioManagerContract {

    void showItems(PortfolioAdapterContract adapter, List<PortfolioCoin> list);

    PortfolioCoin getItem(int position);

    List<PortfolioCoin> getItems();
}
