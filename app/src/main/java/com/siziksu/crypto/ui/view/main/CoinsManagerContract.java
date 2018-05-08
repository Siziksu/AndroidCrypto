package com.siziksu.crypto.ui.view.main;

import com.siziksu.crypto.ui.model.Coin;

import java.util.List;

public interface CoinsManagerContract {

    void showItems(CoinsAdapterContract adapter, List<Coin> list);

    Coin getItem(int position);

    List<Coin> getItems();
}
