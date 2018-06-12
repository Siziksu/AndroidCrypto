package com.siziksu.crypto.domain.main;

import com.siziksu.crypto.common.managers.ThrowableManager;
import com.siziksu.crypto.common.managers.model.Info;
import com.siziksu.crypto.data.RepositoryContract;
import com.siziksu.crypto.domain.mapper.CoinMapper;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public final class MainDomain implements MainDomainContract<MainDomainPresenterContract> {

    @Inject
    RepositoryContract repository;

    private Disposable disposable;
    private MainDomainPresenterContract presenter;

    public MainDomain(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void register(MainDomainPresenterContract presenter) {
        this.presenter = presenter;
    }

    @Override
    public void unregister() {
        presenter = null;
        clearDisposable();
    }

    @Override
    public void start() {
        if (presenter != null) {
            presenter.showLoadingDialog();
        }
        clearDisposable();
        disposable = repository.getCoins()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        coinsDataModel -> {
                            if (presenter != null) {
                                presenter.showCoinList(new CoinMapper().map(coinsDataModel));
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
            case ThrowableManager.PERSISTENCE_COINS_NULL:
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

    private void clearDisposable() {
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }
}
