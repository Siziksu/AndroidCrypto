package com.siziksu.crypto.domain.detail;

import android.graphics.Color;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.siziksu.crypto.domain.model.CoinHistoricalDomainModel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class LineChartHelper {

    public LineData buildAndGetTheLineDataForTheLineChart(@Nullable String label, List<CoinHistoricalDomainModel> coinsHistorical) {
        List<Entry> dataSet1Values = getDataSetValues(coinsHistorical);
        LineDataSet dataSet1 = getLineDataSet(label, dataSet1Values);
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet1);
        return new LineData(dataSets);
    }

    private List<Entry> getDataSetValues(List<CoinHistoricalDomainModel> coinsHistorical) {
        List<Entry> dataSet1Values = new ArrayList<>();
        for (int i = 0; i < coinsHistorical.size(); i++) {
            dataSet1Values.add(new Entry(i, Float.valueOf(coinsHistorical.get(i).priceUsd)));
        }
        return dataSet1Values;
    }

    private LineDataSet getLineDataSet(String label, List<Entry> dataSet1Values) {
        if (label == null) {
            label = "Historical data of the price (USD)";
        }
        LineDataSet dataSet1 = new LineDataSet(dataSet1Values, label);
        dataSet1.setColor(Color.RED);
        dataSet1.setCircleColor(Color.RED);
        return dataSet1;
    }
}
