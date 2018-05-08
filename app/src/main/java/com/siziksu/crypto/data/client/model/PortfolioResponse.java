package com.siziksu.crypto.data.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PortfolioResponse {

    @SerializedName("coins")
    @Expose
    public List<PortfolioCoinClientModel> coins = new ArrayList<>();
}
