package com.siziksu.crypto.presenter.main;

import com.siziksu.crypto.presenter.BaseViewContract;
import com.siziksu.crypto.ui.model.Coin;

import java.util.List;

public interface MainViewContract extends BaseViewContract {

    void showLoadingDialog();

    void hideLoadingDialog();

    void showCoinList(List<Coin> list);

    void showMessage(String message);
}
