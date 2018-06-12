package com.siziksu.crypto.domain;

public interface BaseDomainContract<D> {

    void register(D presenter);

    void unregister();
}
