package com.siziksu.crypto.ui.view.portfolio;

import android.support.v7.widget.RecyclerView;

import com.siziksu.crypto.ui.model.PortfolioCoin;

import java.util.List;

public interface PortfolioAdapterContract {

    void init();

    PortfolioCoin getItem(int position);

    RecyclerView.LayoutManager getLayoutManager();

    void showItems(List<PortfolioCoin> list);

    RecyclerView.Adapter getAdapter();

    void notifyDataSetChanged();
}
