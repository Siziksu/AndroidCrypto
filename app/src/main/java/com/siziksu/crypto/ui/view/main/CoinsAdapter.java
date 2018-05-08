package com.siziksu.crypto.ui.view.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siziksu.crypto.R;
import com.siziksu.crypto.common.utils.MathUtils;
import com.siziksu.crypto.ui.model.Coin;

import java.util.List;

public final class CoinsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements CoinsAdapterContract {

    private Context context;
    private CoinsManagerContract coinsManager;

    private ClickListener listener;
    private LinearLayoutManager layoutManager;

    public CoinsAdapter(Context context, CoinsManagerContract manager) {
        this.context = context;
        this.coinsManager = manager;
    }

    public void init(ClickListener listener) {
        this.listener = listener;
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.coin_row, parent, false);
        return new CoinsViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CoinsViewHolder) {
            CoinsViewHolder localHolder = (CoinsViewHolder) holder;
            Coin coin = coinsManager.getItems().get(position);
            localHolder.coinName.setText(String.format(context.getString(R.string.name_value), coin.name, coin.symbol));
            localHolder.coinPriceUsd.setText(String.format(context.getString(R.string.price_usd_value), MathUtils.roundAndFormatNumber(coin.priceUsd)));
            int color = MathUtils.isPositive(coin.percentChange24h) ? R.color.positiveChange : R.color.negativeChange;
            localHolder.coinPercentChange24h.setTextColor(ContextCompat.getColor(context, color));
            localHolder.coinPercentChange24h.setText(String.format(context.getString(R.string.change_24h_value), MathUtils.roundAndFormatNumber(coin.percentChange24h)));
            localHolder.coinRank.setText(String.valueOf(coin.rank));
        }
    }

    @Override
    public int getItemCount() {
        return coinsManager.getItems().size();
    }

    @Override
    public Coin getItem(int position) {
        return coinsManager.getItem(position);
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }

    @Override
    public void showItems(List<Coin> list) {
        coinsManager.showItems(this, list);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return this;
    }

    interface ClickListener {

        void onClick(View view, int position);
    }
}
