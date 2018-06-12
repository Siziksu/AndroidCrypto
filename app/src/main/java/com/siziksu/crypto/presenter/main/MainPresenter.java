package com.siziksu.crypto.presenter.main;

import com.siziksu.crypto.domain.main.MainDomainContract;
import com.siziksu.crypto.domain.main.MainDomainPresenterContract;
import com.siziksu.crypto.domain.model.CoinDomainModel;
import com.siziksu.crypto.presenter.mapper.CoinMapper;
import com.siziksu.crypto.presenter.model.Coin;
import com.siziksu.crypto.ui.router.RouterContract;

import java.util.List;

import javax.inject.Inject;

public class MainPresenter implements MainPresenterContract<MainViewContract>, MainDomainPresenterContract {

    @Inject
    RouterContract router;
    @Inject
    MainDomainContract<MainDomainPresenterContract> domain;

    private MainViewContract view;

    public MainPresenter(RouterContract router, MainDomainContract<MainDomainPresenterContract> domain) {
        this.router = router;
        this.domain = domain;
    }

    @Override
    public void register(MainViewContract view) {
        this.view = view;
        domain.register(this);
    }

    @Override
    public void unregister() {
        view = null;
        domain.unregister();
    }

    @Override
    public void start() {
        domain.start();
    }

    @Override
    public void onCoinClick(Coin coin) {
        if (view != null) {
            router.goToDetail(view.getAppCompatActivity(), coin);
        }
    }

    @Override
    public void onMenuPortfolioClick() {
        if (view != null) {
            router.goToPortfolio(view.getAppCompatActivity());
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
    public void showCoinList(List<CoinDomainModel> list) {
        if (view != null) {
            view.showCoinList(new CoinMapper().map(list));
        }
    }

    @Override
    public void showMessage(String message) {
        if (view != null) {
            view.showMessage(message);
        }
    }
}
