package com.siziksu.crypto.data.client.service;

public interface RetrofitServiceFactoryContract {

    /**
     * Creates a retrofit service from an arbitrary class (clazz).
     *
     * @param clazz    Java interface of the retrofit service
     * @param endPoint REST endpoint url
     *
     * @return Retrofit service
     */
    <T> T createService(Class<T> clazz, String endPoint);
}
