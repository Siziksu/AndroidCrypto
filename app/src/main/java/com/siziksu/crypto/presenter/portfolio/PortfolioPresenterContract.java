package com.siziksu.crypto.presenter.portfolio;

import com.siziksu.crypto.presenter.BasePresenterContract;
import com.siziksu.crypto.presenter.BaseViewContract;

public interface PortfolioPresenterContract<V extends BaseViewContract> extends BasePresenterContract<V> {

    void start();
}
