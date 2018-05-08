package com.siziksu.crypto.presenter.detail;

import com.github.mikephil.charting.data.LineData;
import com.siziksu.crypto.presenter.BaseViewContract;

public interface DetailViewContract extends BaseViewContract {

    void showLoadingDialog();

    void hideLoadingDialog();

    void drawDataInTheLineChart(LineData data);

    void showMessage(String message);
}
