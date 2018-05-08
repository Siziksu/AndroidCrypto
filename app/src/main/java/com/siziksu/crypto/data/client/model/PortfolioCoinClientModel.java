package com.siziksu.crypto.data.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PortfolioCoinClientModel {

    @SerializedName("coin_id")
    @Expose
    public int coinId;
    @SerializedName("amount")
    @Expose
    public float amount;
    @SerializedName("price_usd")
    @Expose
    public float priceUsd;
    @SerializedName("user_id")
    @Expose
    public int userId;
    @SerializedName("total_usd")
    @Expose
    public float totalUsd;
    @SerializedName("notes")
    @Expose
    public String notes;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("traded_at")
    @Expose
    public String tradedAt;
}
