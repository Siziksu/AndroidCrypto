package com.siziksu.crypto.data.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoinsResponse {

    @SerializedName("coins")
    @Expose
    public CoinsClientModel coins;
}
