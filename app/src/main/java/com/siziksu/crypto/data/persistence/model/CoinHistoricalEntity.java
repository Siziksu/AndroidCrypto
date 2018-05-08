package com.siziksu.crypto.data.persistence.model;

import io.realm.RealmObject;

public class CoinHistoricalEntity extends RealmObject {

    public String priceUsd;
    public String snapshotAt;
}
