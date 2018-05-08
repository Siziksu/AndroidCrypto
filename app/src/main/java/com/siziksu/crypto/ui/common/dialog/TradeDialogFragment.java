package com.siziksu.crypto.ui.common.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.siziksu.crypto.R;
import com.siziksu.crypto.common.function.Predicate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TradeDialogFragment extends DialogFragment {

    @BindView(R.id.dialogAmount)
    EditText dialogAmount;

    private Predicate<Float> trade;

    public TradeDialogFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.dialog_trade, container);
        ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @Override
    public int getTheme() {
        return R.style.AppTheme_AppCompatDialogStyle_NoTitle;
    }

    @Override
    public void onDestroyView() {
        Dialog dialog = getDialog();
        if (dialog != null && getRetainInstance()) {
            dialog.setDismissMessage(null);
        }
        super.onDestroyView();
    }

    @OnClick({R.id.dialogTrade, R.id.dialogCancel})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.dialogTrade:
                String value = dialogAmount.getText().toString();
                if (trade != null) {
                    trade.supply(Float.valueOf(TextUtils.isEmpty(value) ? "0" : value));
                }
                dismiss();
                break;
            case R.id.dialogCancel:
                dismiss();
                break;
        }
    }

    public void setCallback(Predicate<Float> trade) {
        this.trade = trade;
    }
}
