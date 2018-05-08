package com.siziksu.crypto.dagger.module;

import android.content.Context;

import com.siziksu.crypto.common.managers.ConnectionManager;
import com.siziksu.crypto.data.Repository;
import com.siziksu.crypto.data.RepositoryContract;
import com.siziksu.crypto.data.client.CryptoClient;
import com.siziksu.crypto.data.client.CryptoClientContract;
import com.siziksu.crypto.data.client.service.RetrofitServiceFactory;
import com.siziksu.crypto.data.client.service.RetrofitServiceFactoryContract;
import com.siziksu.crypto.data.persistence.PersistenceClient;
import com.siziksu.crypto.data.persistence.PersistenceClientContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class DataModule {

    @Singleton
    @Provides
    RepositoryContract providesRepository(Context context,
                                          ConnectionManager connectionManager,
                                          PersistenceClientContract persistenceClient,
                                          CryptoClientContract cryptoClient) {
        return new Repository(context, connectionManager, persistenceClient, cryptoClient);
    }

    @Singleton
    @Provides
    RetrofitServiceFactoryContract providesRetrofitServiceFactory() {
        return new RetrofitServiceFactory();
    }

    @Singleton
    @Provides
    PersistenceClientContract providesPersistenceClient() {
        return new PersistenceClient();
    }

    @Singleton
    @Provides
    CryptoClientContract providesCryptoClient(RetrofitServiceFactoryContract retrofitServiceFactory) {
        return new CryptoClient(retrofitServiceFactory);
    }
}
