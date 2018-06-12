package com.siziksu.crypto.domain.portfolio;

import com.siziksu.crypto.common.Constants;
import com.siziksu.crypto.common.managers.ThrowableManager;
import com.siziksu.crypto.common.managers.model.Info;
import com.siziksu.crypto.data.RepositoryContract;
import com.siziksu.crypto.data.model.CoinDataModel;
import com.siziksu.crypto.domain.mapper.PortfolioCoinMapper;
import com.siziksu.crypto.domain.model.PortfolioCoinDomainModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.SingleSubject;

public final class PortfolioDomain implements PortfolioDomainContract<PortfolioDomainPresenterContract> {

    @Inject
    RepositoryContract repository;

    private Disposable[] disposable = new Disposable[2];
    private PortfolioDomainPresenterContract presenter;

    public PortfolioDomain(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void register(PortfolioDomainPresenterContract presenter) {
        this.presenter = presenter;
    }

    @Override
    public void unregister() {
        presenter = null;
        disposable = new Disposable[2];
    }

    @Override
    public void start() {
        if (presenter != null) {
            presenter.showLoadingDialog();
        }
        clearDisposable(0);
        disposable[0] = repository.getPortfolio(Constants.CREDENTIALS[0], Constants.CREDENTIALS[1])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        portfolioCoinsDataModel -> {
                            if (presenter != null) {
                                addTheMissingValuesForTheCoinsAndShowPortfolio(new PortfolioCoinMapper().map(portfolioCoinsDataModel));
                            }
                        },
                        this::showError
                );
    }

    private void addTheMissingValuesForTheCoinsAndShowPortfolio(List<PortfolioCoinDomainModel> portfolioCoins) {
        List<Single<?>> singles = new ArrayList<>();
        getCoinsAndAddMissingValues(portfolioCoins, singles);
        waitForAllTheChangesAndShowPortfolio(portfolioCoins, singles);
    }

    private void getCoinsAndAddMissingValues(List<PortfolioCoinDomainModel> portfolioCoins, List<Single<?>> singles) {
        for (PortfolioCoinDomainModel portfolioCoin : portfolioCoins) {
            singles.add(repository.getCoin(portfolioCoin.coinId)
                                .subscribeOn(Schedulers.io())
                                .doOnSuccess(coin -> addTheMissingValues(portfolioCoin, coin)));
        }
    }

    private void addTheMissingValues(PortfolioCoinDomainModel portfolioCoin, CoinDataModel coin) {
        portfolioCoin.name = coin.name;
        portfolioCoin.symbol = coin.symbol;
        portfolioCoin.price = coin.priceUsd;
    }

    private void waitForAllTheChangesAndShowPortfolio(List<PortfolioCoinDomainModel> portfolioCoins, List<Single<?>> singles) {
        clearDisposable(1);
        disposable[1] = SingleSubject.zip(singles, coin -> coin)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            if (presenter != null) {
                                presenter.showPortfolioCoinList(portfolioCoins);
                                presenter.hideLoadingDialog();
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
        if (presenter != null) {
            presenter.showMessage(message);
            presenter.hideLoadingDialog();
        }
    }

    private void clearDisposable(int index) {
        if (disposable[index] != null) {
            disposable[index].dispose();
            disposable[index] = null;
        }
    }
}
