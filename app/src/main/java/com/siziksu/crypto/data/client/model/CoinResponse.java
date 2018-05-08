package com.siziksu.crypto.data.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoinResponse {

    @SerializedName("coin")
    @Expose
    public CoinClientModel coin;
}
