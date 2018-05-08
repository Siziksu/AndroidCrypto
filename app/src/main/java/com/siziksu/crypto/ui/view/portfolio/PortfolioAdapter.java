package com.siziksu.crypto.ui.view.portfolio;

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
import com.siziksu.crypto.ui.model.PortfolioCoin;

import java.util.List;

public final class PortfolioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements PortfolioAdapterContract {

    private Context context;
    private PortfolioManagerContract coinsManager;

    private LinearLayoutManager layoutManager;

    public PortfolioAdapter(Context context, PortfolioManagerContract manager) {
        this.context = context;
        this.coinsManager = manager;
    }

    public void init() {
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.portfolio_coin_row, parent, false);
        return new PortfolioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PortfolioViewHolder) {
            PortfolioViewHolder localHolder = (PortfolioViewHolder) holder;
            PortfolioCoin coin = coinsManager.getItems().get(position);
            String name = String.format(context.getString(R.string.name_value), coin.name, coin.symbol);
            localHolder.portfolioCoinName.setText(name);
            localHolder.portfolioCoinPriceUsd.setText(String.format(context.getString(R.string.price_usd_value), MathUtils.roundAndFormatNumber(coin.price)));
            int color = MathUtils.isPositive(String.valueOf(coin.amount)) ? R.color.positiveChange : R.color.negativeChange;
            localHolder.portfolioCoinAmount.setTextColor(ContextCompat.getColor(context, color));
            localHolder.portfolioCoinAmount.setText(String.valueOf(coin.amount));
            localHolder.portfolioCoinTotalUsd.setText(String.format(context.getString(R.string.price_usd_value), MathUtils.roundAndFormatNumber(String.valueOf(coin.priceUsd))));
        }
    }

    @Override
    public int getItemCount() {
        return coinsManager.getItems().size();
    }

    @Override
    public PortfolioCoin getItem(int position) {
        return coinsManager.getItem(position);
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }

    @Override
    public void showItems(List<PortfolioCoin> list) {
        coinsManager.showItems(this, list);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return this;
    }
}
