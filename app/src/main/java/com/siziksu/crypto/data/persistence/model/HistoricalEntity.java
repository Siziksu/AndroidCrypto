package com.siziksu.crypto.data.persistence.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HistoricalEntity extends RealmObject {

    @PrimaryKey
    public int id;
    public RealmList<CoinHistoricalEntity> historical = new RealmList<>();
}
