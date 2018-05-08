package com.siziksu.crypto.data.persistence.model;

import io.realm.RealmObject;

public class PortfolioCoinEntity extends RealmObject {

    public int coinId;
    public float amount;
    public float priceUsd;
}
