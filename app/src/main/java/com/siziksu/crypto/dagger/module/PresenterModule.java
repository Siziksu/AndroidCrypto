package com.siziksu.crypto.dagger.module;

import com.siziksu.crypto.data.RepositoryContract;
import com.siziksu.crypto.domain.detail.DetailDomainContract;
import com.siziksu.crypto.domain.detail.DetailDomainPresenterContract;
import com.siziksu.crypto.domain.main.MainDomainContract;
import com.siziksu.crypto.domain.main.MainDomainPresenterContract;
import com.siziksu.crypto.domain.portfolio.PortfolioDomainContract;
import com.siziksu.crypto.domain.portfolio.PortfolioDomainPresenterContract;
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
    MainPresenterContract<MainViewContract> providesMainPresenter(RouterContract router,
                                                                  MainDomainContract<MainDomainPresenterContract> domain) {
        return new MainPresenter(router, domain);
    }

    @Singleton
    @Provides
    DetailPresenterContract<DetailViewContract> providesDetailPresenter(DetailDomainContract<DetailDomainPresenterContract> domain) {
        return new DetailPresenter(domain);
    }

    @Singleton
    @Provides
    PortfolioPresenterContract<PortfolioViewContract> providesPortfolioPresenter(PortfolioDomainContract<PortfolioDomainPresenterContract> domain) {
        return new PortfolioPresenter(domain);
    }
}
