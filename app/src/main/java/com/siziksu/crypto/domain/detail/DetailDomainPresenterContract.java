package com.siziksu.crypto.domain.detail;

import com.github.mikephil.charting.data.LineData;
import com.siziksu.crypto.presenter.model.Coin;

public interface DetailDomainPresenterContract {

    void showLoadingDialog();

    void hideLoadingDialog();

    void showMessage(String message);

    String getString(int resId);

    void drawDataInTheLineChart(LineData data);

    void showTradeDialog(Coin coin);
}
