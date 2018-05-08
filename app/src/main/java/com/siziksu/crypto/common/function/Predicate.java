package com.siziksu.crypto.common.function;

@FunctionalInterface
public interface Predicate<T> {

    void supply(T object);
}
