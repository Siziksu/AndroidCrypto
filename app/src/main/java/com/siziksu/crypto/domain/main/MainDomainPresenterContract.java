package com.siziksu.crypto.domain.main;

import com.siziksu.crypto.domain.model.CoinDomainModel;

import java.util.List;

public interface MainDomainPresenterContract {

    void showLoadingDialog();

    void hideLoadingDialog();

    void showCoinList(List<CoinDomainModel> list);

    void showMessage(String message);
}
