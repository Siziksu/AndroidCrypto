package com.siziksu.crypto.data.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoinClientModel {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("symbol")
    @Expose
    public String symbol;
    @SerializedName("logo")
    @Expose
    public String logo;
    @SerializedName("rank")
    @Expose
    public int rank;
    @SerializedName("price_usd")
    @Expose
    public String priceUsd;
    @SerializedName("price_btc")
    @Expose
    public String priceBtc;
    @SerializedName("24h_volume_usd")
    @Expose
    public long volumeUsd24h;
    @SerializedName("market_cap_usd")
    @Expose
    public long marketCapUsd;
    @SerializedName("available_supply")
    @Expose
    public long availableSupply;
    @SerializedName("total_supply")
    @Expose
    public long totalSupply;
    @SerializedName("percent_change_1h")
    @Expose
    public String percentChange1h;
    @SerializedName("percent_change_24h")
    @Expose
    public String percentChange24h;
    @SerializedName("percent_change_7d")
    @Expose
    public String percentChange7d;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
}
