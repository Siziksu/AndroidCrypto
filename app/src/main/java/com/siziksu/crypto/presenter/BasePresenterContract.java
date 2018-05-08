package com.siziksu.crypto.presenter;

public interface BasePresenterContract<V extends BaseViewContract> {

    void register(final V view);

    void unregister();
}
