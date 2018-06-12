package com.siziksu.crypto.presenter.main;

import com.siziksu.crypto.presenter.BasePresenterContract;
import com.siziksu.crypto.presenter.BaseViewContract;
import com.siziksu.crypto.presenter.model.Coin;

public interface MainPresenterContract<V extends BaseViewContract> extends BasePresenterContract<V> {

    void start();

    void onCoinClick(Coin coin);

    void onMenuPortfolioClick();
}
