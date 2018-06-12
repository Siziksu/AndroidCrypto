package com.siziksu.crypto.presenter.detail;

import com.github.mikephil.charting.data.LineData;
import com.siziksu.crypto.R;
import com.siziksu.crypto.domain.detail.DetailDomainContract;
import com.siziksu.crypto.domain.detail.DetailDomainPresenterContract;
import com.siziksu.crypto.presenter.mapper.CoinMapper;
import com.siziksu.crypto.presenter.model.Coin;
import com.siziksu.crypto.ui.common.DialogFragmentHelper;

import javax.inject.Inject;

public class DetailPresenter implements DetailPresenterContract<DetailViewContract>, DetailDomainPresenterContract {

    @Inject
    DetailDomainContract<DetailDomainPresenterContract> domain;

    private DetailViewContract view;

    public DetailPresenter(DetailDomainContract<DetailDomainPresenterContract> domain) {
        this.domain = domain;
    }

    @Override
    public void register(DetailViewContract view) {
        this.view = view;
        domain.register(this);
    }

    @Override
    public void unregister() {
        view = null;
        domain.unregister();
    }

    @Override
    public void start(int coinId) {
        domain.start(coinId);
    }

    @Override
    public void onCoinTradeClick(Coin coin) {
        if (view != null) {
            DialogFragmentHelper.showTradeDialog(view.getAppCompatActivity(), amount -> {
                if (amount != 0) {
                    domain.trade(new CoinMapper().unMap(coin), amount);
                } else {
                    if (view != null) {
                        view.showMessage(view.getAppCompatActivity().getString(R.string.trade_amount_can_not_be_zero));
                    }
                }
            });
        }
    }

    @Override
    public void showLoadingDialog() {
        if (view != null) {
            view.showLoadingDialog();
        }
    }

    @Override
    public void hideLoadingDialog() {
        if (view != null) {
            view.hideLoadingDialog();
        }
    }

    @Override
    public void showMessage(String message) {
        if (view != null) {
            view.showMessage(message);
        }
    }

    @Override
    public String getString(int resId) {
        return view != null ? view.getAppCompatActivity().getString(resId) : "";
    }

    @Override
    public void drawDataInTheLineChart(LineData data) {
        if (view != null) {
            view.drawDataInTheLineChart(data);
        }
    }

    @Override
    public void showTradeDialog(Coin coin) {
        if (view != null) {
            DialogFragmentHelper.showTradeDialog(view.getAppCompatActivity(), amount -> {
                if (amount != 0) {
                    domain.trade(new CoinMapper().unMap(coin), amount);
                } else {
                    if (view != null) {
                        view.showMessage(view.getAppCompatActivity().getString(R.string.trade_amount_can_not_be_zero));
                    }
                }
            });
        }
    }
}
