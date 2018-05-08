package com.siziksu.crypto.common.mapper;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * @param <E> entity
 * @param <M> model
 */
public abstract class RealmMapper<E, M> implements RealmBaseMapper<E, M> {

    @Override
    public List<M> map(RealmList<E> entityList) {
        List<M> modelList = new ArrayList<>();
        for (E entity : entityList) {
            modelList.add(map(entity));
        }
        return modelList;
    }

    @Override
    public RealmList<E> unMap(List<M> model) {
        RealmList<E> entityList = new RealmList<>();
        for (M entity : model) {
            entityList.add(unMap(entity));
        }
        return entityList;
    }
}
