package com.siziksu.crypto.data.model;

import java.util.ArrayList;
import java.util.List;

public class CoinsDataModel {

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
    public List<CoinDataModel> data = new ArrayList<>();
}
