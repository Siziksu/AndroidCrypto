package com.siziksu.crypto.dagger.module;

import com.siziksu.crypto.data.RepositoryContract;
import com.siziksu.crypto.presenter.detail.DetailPresenter;
import com.siziksu.crypto.presenter.detail.DetailPresenterContract;
import com.siziksu.crypto.presenter.detail.DetailViewContract;
import com.siziksu.crypto.presenter.main.MainPresenter;
import com.siziksu.crypto.presenter.main.MainPresenterContract;
import com.siziksu.crypto.presenter.main.MainViewContract;
import com.siziksu.crypto.presenter.portfolio.PortfolioPresenter;
import com.siziksu.crypto.presenter.portfolio.PortfolioPresenterContract;
import com.siziksu.crypto.presenter.portfolio.PortfolioViewContract;
import com.siziksu.crypto.ui.router.RouterContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class PresenterModule {

    @Singleton
    @Provides
    MainPresenterContract<MainViewContract> providesMainPresenter(RouterContract router, RepositoryContract repository) {
        return new MainPresenter(router, repository);
    }

    @Singleton
    @Provides
    DetailPresenterContract<DetailViewContract> providesDetailPresenter(RepositoryContract repository) {
        return new DetailPresenter(repository);
    }

    @Singleton
    @Provides
    PortfolioPresenterContract<PortfolioViewContract> providesPortfolioPresenter(RepositoryContract repository) {
        return new PortfolioPresenter(repository);
    }
}
