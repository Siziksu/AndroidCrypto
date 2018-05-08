package com.siziksu.crypto.ui.view.portfolio;

import android.util.Log;

import com.siziksu.crypto.common.Constants;
import com.siziksu.crypto.ui.model.PortfolioCoin;

import java.util.ArrayList;
import java.util.List;

public final class PortfolioManager implements PortfolioManagerContract {

    private List<PortfolioCoin> coins = new ArrayList<>();

    public PortfolioManager() {}

    @Override
    public void showItems(PortfolioAdapterContract adapter, List<PortfolioCoin> list) {
        coins.clear();
        coins.addAll(list);
        Log.i(Constants.TAG, "Total portfolio coins: " + coins.size());
        adapter.notifyDataSetChanged();
    }

    @Override
    public PortfolioCoin getItem(int position) {
        return coins.get(position);
    }

    @Override
    public List<PortfolioCoin> getItems() {
        return coins;
    }
}
