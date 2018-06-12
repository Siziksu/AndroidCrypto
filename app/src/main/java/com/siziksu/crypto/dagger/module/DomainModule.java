package com.siziksu.crypto.dagger.module;

import com.siziksu.crypto.data.RepositoryContract;
import com.siziksu.crypto.domain.detail.DetailDomain;
import com.siziksu.crypto.domain.detail.DetailDomainContract;
import com.siziksu.crypto.domain.detail.DetailDomainPresenterContract;
import com.siziksu.crypto.domain.main.MainDomain;
import com.siziksu.crypto.domain.main.MainDomainContract;
import com.siziksu.crypto.domain.main.MainDomainPresenterContract;
import com.siziksu.crypto.domain.portfolio.PortfolioDomain;
import com.siziksu.crypto.domain.portfolio.PortfolioDomainContract;
import com.siziksu.crypto.domain.portfolio.PortfolioDomainPresenterContract;

import dagger.Module;
import dagger.Provides;

@Module
public final class DomainModule {

    @Provides
    MainDomainContract<MainDomainPresenterContract> providesMainDomain(RepositoryContract repository) {
        return new MainDomain(repository);
    }

    @Provides
    DetailDomainContract<DetailDomainPresenterContract> providesDetailDomain(RepositoryContract repository) {
        return new DetailDomain(repository);
    }

    @Provides
    PortfolioDomainContract<PortfolioDomainPresenterContract> providesPortfolioDomain(RepositoryContract repository) {
        return new PortfolioDomain(repository);
    }
}
