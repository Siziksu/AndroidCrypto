package com.siziksu.crypto.common.mapper;

import java.util.List;

import io.realm.RealmList;

/**
 * @param <E> entity
 * @param <M> model
 */
public interface RealmBaseMapper<E, M> {

    List<M> map(RealmList<E> entityList);

    RealmList<E> unMap(List<M> modelList);

    M map(E entity);

    E unMap(M model);
}

