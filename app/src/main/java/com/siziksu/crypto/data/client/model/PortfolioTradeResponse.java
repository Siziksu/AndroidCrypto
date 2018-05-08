package com.siziksu.crypto.data.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PortfolioTradeResponse {

    @SerializedName("trade")
    @Expose
    public PortfolioCoinClientModel trade;
}
