package com.siziksu.crypto.ui.view.portfolio;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.siziksu.crypto.R;

import butterknife.BindView;
import butterknife.ButterKnife;

final class PortfolioViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.portfolioCoinCardView)
    View coinCardView;
    @BindView(R.id.portfolioCoinName)
    TextView portfolioCoinName;
    @BindView(R.id.portfolioCoinPriceUsd)
    TextView portfolioCoinPriceUsd;
    @BindView(R.id.portfolioCoinAmount)
    TextView portfolioCoinAmount;
    @BindView(R.id.portfolioCoinTotalUsd)
    TextView portfolioCoinTotalUsd;

    PortfolioViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
