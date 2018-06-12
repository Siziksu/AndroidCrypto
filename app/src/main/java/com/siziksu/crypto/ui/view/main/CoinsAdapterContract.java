package com.siziksu.crypto.ui.view.main;

import android.support.v7.widget.RecyclerView;

import com.siziksu.crypto.presenter.model.Coin;

import java.util.List;

public interface CoinsAdapterContract {

    void init(CoinsAdapter.ClickListener listener);

    Coin getItem(int position);

    RecyclerView.LayoutManager getLayoutManager();

    void showItems(List<Coin> list);

    RecyclerView.Adapter getAdapter();

    void notifyDataSetChanged();
}
