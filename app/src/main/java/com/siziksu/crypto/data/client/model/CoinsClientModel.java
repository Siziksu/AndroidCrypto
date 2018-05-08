package com.siziksu.crypto.data.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CoinsClientModel {

    @SerializedName("total")
    @Expose
    public int total;
    @SerializedName("per_page")
    @Expose
    public int perPage;
    @SerializedName("current_page")
    @Expose
    public int currentPage;
    @SerializedName("last_page")
    @Expose
    public int lastPage;
    @SerializedName("first_page_url")
    @Expose
    public String firstPageUrl;
    @SerializedName("last_page_url")
    @Expose
    public String lastPageUrl;
    @SerializedName("next_page_url")
    @Expose
    public String nextPageUrl;
    @SerializedName("prev_page_url")
    @Expose
    public String prevPageUrl;
    @SerializedName("path")
    @Expose
    public String path;
    @SerializedName("from")
    @Expose
    public int from;
    @SerializedName("to")
    @Expose
    public int to;
    @SerializedName("data")
    @Expose
    public List<CoinClientModel> data = new ArrayList<>();
}
