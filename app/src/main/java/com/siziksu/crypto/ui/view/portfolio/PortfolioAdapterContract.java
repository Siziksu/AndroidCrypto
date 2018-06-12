package com.siziksu.crypto.ui.view.portfolio;

import android.support.v7.widget.RecyclerView;

import com.siziksu.crypto.presenter.model.PortfolioCoin;

import java.util.List;

public interface PortfolioAdapterContract {

    void init();

    void notifyDataSetChanged();

    RecyclerView.LayoutManager getLayoutManager();

    RecyclerView.Adapter getAdapter();

    void showItems(List<PortfolioCoin> list);
}
