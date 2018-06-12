package com.siziksu.crypto.ui.view.main;

import android.util.Log;

import com.siziksu.crypto.common.Constants;
import com.siziksu.crypto.presenter.model.Coin;

import java.util.ArrayList;
import java.util.List;

public final class CoinsManager implements CoinsManagerContract {

    private List<Coin> coins = new ArrayList<>();

    public CoinsManager() {}

    @Override
    public void showItems(CoinsAdapterContract adapter, List<Coin> list) {
        coins.clear();
        coins.addAll(list);
        Log.i(Constants.TAG, "Total coins: " + coins.size());
        adapter.notifyDataSetChanged();
    }

    @Override
    public Coin getItem(int position) {
        return coins.get(position);
    }

    @Override
    public List<Coin> getItems() {
        return coins;
    }
}
