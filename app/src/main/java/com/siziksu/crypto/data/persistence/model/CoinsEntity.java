package com.siziksu.crypto.data.persistence.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class CoinsEntity extends RealmObject {

    public int total;
    public int perPage;
    public int currentPage;
    public int lastPage;
    public String firstPageUrl;
    public String lastPageUrl;
    public String nextPageUrl;
    public String prevPageUrl;
    public String path;
    public int from;
    public int to;
    public RealmList<CoinEntity> data = new RealmList<>();
}
