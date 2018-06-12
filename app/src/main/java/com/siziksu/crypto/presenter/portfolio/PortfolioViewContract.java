package com.siziksu.crypto.presenter.portfolio;

import com.siziksu.crypto.presenter.BaseViewContract;
import com.siziksu.crypto.presenter.model.PortfolioCoin;

import java.util.List;

public interface PortfolioViewContract extends BaseViewContract {

    void showLoadingDialog();

    void hideLoadingDialog();

    void showPortfolioCoinList(List<PortfolioCoin> list);

    void showMessage(String message);
}
