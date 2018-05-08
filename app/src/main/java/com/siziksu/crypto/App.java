package com.siziksu.crypto;

import android.app.Application;

import com.siziksu.crypto.dagger.component.ApplicationComponent;
import com.siziksu.crypto.dagger.component.DaggerApplicationComponent;
import com.siziksu.crypto.dagger.module.ApplicationModule;
import com.siziksu.crypto.dagger.module.DataModule;
import com.siziksu.crypto.dagger.module.PresenterModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private static final String REALM_0 = "realm_0.realm";

    private ApplicationComponent applicationComponent;
    private RealmConfiguration realmConfiguration;

    private static App app;

    public static App get() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initDagger();
        initRealm();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public RealmConfiguration getRealmConfiguration() {
        return realmConfiguration;
    }

    private void initDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule())
                .presenterModule(new PresenterModule())
                .build();
        applicationComponent.inject(this);
    }

    private void initRealm() {
        Realm.init(this);
        realmConfiguration = new RealmConfiguration.Builder().name(REALM_0).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
