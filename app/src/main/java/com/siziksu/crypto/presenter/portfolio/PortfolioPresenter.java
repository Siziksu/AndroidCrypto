package com.siziksu.crypto.presenter.portfolio;

import com.siziksu.crypto.common.managers.ThrowableManager;
import com.siziksu.crypto.common.managers.model.Info;
import com.siziksu.crypto.data.RepositoryContract;
import com.siziksu.crypto.presenter.mapper.PortfolioCoinMapper;
import com.siziksu.crypto.ui.model.PortfolioCoin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.SingleSubject;

public class PortfolioPresenter implements PortfolioPresenterContract<PortfolioViewContract> {

    @Inject
    RepositoryContract repository;

    private PortfolioViewContract view;
    private Disposable disposable;

    public PortfolioPresenter(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void register(PortfolioViewContract view) {
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
        disposable = repository.getPortfolio("richard@rich.com", "secret")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        portfolioCoinsDataModel -> {
                            if (view != null) {
                                addTheMissingValuesForTheCoinsAndShowPortfolio(new PortfolioCoinMapper().map(portfolioCoinsDataModel));
                            }
                        },
                        this::showError
                );
    }

    private void addTheMissingValuesForTheCoinsAndShowPortfolio(List<PortfolioCoin> portfolioCoins) {
        List<Single<?>> singles = new ArrayList<>();
        for (PortfolioCoin portfolioCoin : portfolioCoins) {
            singles.add(repository.getCoin(portfolioCoin.coinId)
                                .subscribeOn(Schedulers.io())
                                .doOnSuccess(coin -> {
                                    portfolioCoin.name = coin.name;
                                    portfolioCoin.symbol = coin.symbol;
                                    portfolioCoin.price = coin.priceUsd;
                                }));
        }
        cancelLastRequest();
        disposable = SingleSubject.zip(singles, coin -> coin)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            if (view != null) {
                                view.showPortfolioCoinList(portfolioCoins);
                                view.hideLoadingDialog();
                            }

                        },
                        this::showError
                );
    }

    private void showError(Throwable throwable) {
        Info error = ThrowableManager.handleException(throwable);
        String message;
        switch (error.id) {
            case ThrowableManager.UNAUTHORIZED_EXCEPTION:
                message = ThrowableManager.UNAUTHORIZED_EXCEPTION_INFO.info;
                break;
            case ThrowableManager.PERSISTENCE_PORTFOLIO_NULL:
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

    private void cancelLastRequest() {
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }
}
