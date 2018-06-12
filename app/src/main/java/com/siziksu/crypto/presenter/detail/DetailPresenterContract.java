package com.siziksu.crypto.presenter.detail;

import com.siziksu.crypto.presenter.BasePresenterContract;
import com.siziksu.crypto.presenter.BaseViewContract;
import com.siziksu.crypto.presenter.model.Coin;

public interface DetailPresenterContract<V extends BaseViewContract> extends BasePresenterContract<V> {

    void start(int coinId);

    void onCoinTradeClick(Coin coin);
}
