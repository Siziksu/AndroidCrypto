package com.siziksu.crypto.data.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TradeRequest {

    @SerializedName("coin_id")
    @Expose
    public int coinId;
    @SerializedName("amount")
    @Expose
    public float amount;
    @SerializedName("price_usd")
    @Expose
    public float priceUsd;
    @SerializedName("notes")
    @Expose
    public String notes;
    @SerializedName("traded_at")
    @Expose
    public String tradedAt;
}
