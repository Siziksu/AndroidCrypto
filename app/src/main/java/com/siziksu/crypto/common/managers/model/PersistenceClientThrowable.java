package com.siziksu.crypto.common.managers.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PersistenceClientThrowable extends Throwable {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
                    COINS_NULL,
                    COIN_NULL,
                    HISTORICAL_NULL,
                    PORTFOLIO_NULL
            })
    @interface PersistenceErrorType {}

    public static final int COINS_NULL = 0;
    public static final int COIN_NULL = 1;
    public static final int HISTORICAL_NULL = 2;
    public static final int PORTFOLIO_NULL = 3;

    private int type;

    public PersistenceClientThrowable(@PersistenceErrorType int type) {
        super(setMessage(type));
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public PersistenceClientThrowable setType(@PersistenceErrorType int type) {
        this.type = type;
        return this;
    }

    private static String setMessage(int type) {
        switch (type) {
            case PORTFOLIO_NULL:
                return "Portfolio null";
            case HISTORICAL_NULL:
                return "Historical null";
            case COINS_NULL:
                return "Coins null";
            case COIN_NULL:
                return "Coin null";
            default:
                return "Unknown";
        }
    }
}
