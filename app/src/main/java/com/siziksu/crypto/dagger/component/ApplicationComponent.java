package com.siziksu.crypto.dagger.component;

import com.siziksu.crypto.App;
import com.siziksu.crypto.dagger.module.ApplicationModule;
import com.siziksu.crypto.dagger.module.DataModule;
import com.siziksu.crypto.dagger.module.DomainModule;
import com.siziksu.crypto.dagger.module.PresenterModule;
import com.siziksu.crypto.ui.view.detail.DetailActivity;
import com.siziksu.crypto.ui.view.main.MainActivity;
import com.siziksu.crypto.ui.view.portfolio.PortfolioActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                DataModule.class,
                DomainModule.class,
                PresenterModule.class
        }
)
public interface ApplicationComponent {

    void inject(App target);

    void inject(MainActivity target);

    void inject(DetailActivity target);

    void inject(PortfolioActivity target);
}
