package com.siziksu.crypto.presenter.main;

import com.siziksu.crypto.common.managers.ThrowableManager;
import com.siziksu.crypto.common.managers.model.Info;
import com.siziksu.crypto.data.RepositoryContract;
import com.siziksu.crypto.presenter.mapper.CoinMapper;
import com.siziksu.crypto.ui.model.Coin;
import com.siziksu.crypto.ui.router.RouterContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainPresenterContract<MainViewContract> {

    @Inject
    RepositoryContract repository;
    @Inject
    RouterContract router;

    private MainViewContract view;
    private Disposable disposable;

    public MainPresenter(RouterContract router, RepositoryContract repository) {
        this.router = router;
        this.repository = repository;
    }

    @Override
    public void register(MainViewContract view) {
        this.view = view;
    }

    @Override
    public void unregister() {
        view = null;
        cancelLastRequest();
    }

    @Override
    public void start() {
        if (view != null) {
            view.showLoadingDialog();
        }
        cancelLastRequest();
        disposable = repository.getCoins()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        coinsDataModel -> {
                            if (view != null) {
                                view.showCoinList(new CoinMapper().map(coinsDataModel));
                                view.hideLoadingDialog();
                            }
                        },
                        throwable -> {
                            Info error = ThrowableManager.handleException(throwable);
                            String message;
                            switch (error.id) {
                                case ThrowableManager.PERSISTENCE_COINS_NULL:
                                case ThrowableManager.UNKNOWN_HOST_EXCEPTION:
                                case ThrowableManager.CONNECTION_EXCEPTION:
                                    message = ThrowableManager.CONNECTION_EXCEPTION_INFO.info;
                                    break;
                                default:
                                    message = ThrowableManager.GENERIC_EXCEPTION_INFO.info;
                                    break;
                            }
                            if (view != null) {
                                view.showMessage(message);
                                view.hideLoadingDialog();
                            }
                        }
                );
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

    private void cancelLastRequest() {
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }
}
