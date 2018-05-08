package com.siziksu.crypto.data.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoinHistoricalClientModel {

    @SerializedName("price_usd")
    @Expose
    public String priceUsd;
    @SerializedName("snapshot_at")
    @Expose
    public String snapshotAt;
}
