package com.siziksu.crypto.ui.view.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.siziksu.crypto.R;

import butterknife.BindView;
import butterknife.ButterKnife;

final class CoinsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.coinCardView)
    View coinCardView;
    @BindView(R.id.coinName)
    TextView coinName;
    @BindView(R.id.coinPriceUsd)
    TextView coinPriceUsd;
    @BindView(R.id.coinPercentChange24h)
    TextView coinPercentChange24h;
    @BindView(R.id.coinRank)
    TextView coinRank;

    private CoinsAdapter.ClickListener listener;

    CoinsViewHolder(View view, CoinsAdapter.ClickListener listener) {
        super(view);
        ButterKnife.bind(this, view);
        this.listener = listener;
        coinCardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view, getAdapterPosition());
        }
    }
}
