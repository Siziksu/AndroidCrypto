package com.siziksu.crypto.dagger.module;

import android.content.Context;

import com.siziksu.crypto.App;
import com.siziksu.crypto.common.managers.ConnectionManager;
import com.siziksu.crypto.ui.view.main.CoinsAdapter;
import com.siziksu.crypto.ui.view.main.CoinsAdapterContract;
import com.siziksu.crypto.ui.view.main.CoinsManager;
import com.siziksu.crypto.ui.view.main.CoinsManagerContract;
import com.siziksu.crypto.ui.view.portfolio.PortfolioAdapter;
import com.siziksu.crypto.ui.view.portfolio.PortfolioAdapterContract;
import com.siziksu.crypto.ui.view.portfolio.PortfolioManager;
import com.siziksu.crypto.ui.view.portfolio.PortfolioManagerContract;
import com.siziksu.crypto.ui.router.Router;
import com.siziksu.crypto.ui.router.RouterContract;
import com.siziksu.crypto.ui.router.RouterHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {

    private final App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    Context providesContext() {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    ConnectionManager providesConnectionManager(Context context) {
        return new ConnectionManager(context);
    }

    @Singleton
    @Provides
    CoinsManagerContract providesCoinsManager() {
        return new CoinsManager();
    }

    @Singleton
    @Provides
    CoinsAdapterContract providesCoinsAdapter(Context context, CoinsManagerContract coinsManager) {
        return new CoinsAdapter(context, coinsManager);
    }

    @Singleton
    @Provides
    PortfolioManagerContract providesPortfolioManager() {
        return new PortfolioManager();
    }

    @Singleton
    @Provides
    PortfolioAdapterContract providesPortfolioAdapter(Context context, PortfolioManagerContract portfolioManager) {
        return new PortfolioAdapter(context, portfolioManager);
    }

    @Singleton
    @Provides
    RouterContract providesRouter(RouterHelper routerHelper) {
        return new Router(routerHelper);
    }
}
