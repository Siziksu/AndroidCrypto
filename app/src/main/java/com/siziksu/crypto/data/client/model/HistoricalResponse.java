package com.siziksu.crypto.data.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HistoricalResponse {

    @SerializedName("historical")
    @Expose
    public List<CoinHistoricalClientModel> historical = new ArrayList<>();
}
