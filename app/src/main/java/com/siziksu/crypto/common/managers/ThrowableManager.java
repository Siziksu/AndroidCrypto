package com.siziksu.crypto.common.managers;

import com.siziksu.crypto.common.managers.model.Info;
import com.siziksu.crypto.common.managers.model.PersistenceClientThrowable;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * Provides info about a throwable.
 */
public final class ThrowableManager {

    public static final int CONNECTION_EXCEPTION = 0;
    public static final int TIME_OUT_EXCEPTION = 1;
    public static final int UNAUTHORIZED_EXCEPTION = 2;
    public static final int SERVER_EXCEPTION = 3;
    public static final int GENERIC_EXCEPTION = 4;
    public static final int NOT_FOUND_EXCEPTION = 5;
    public static final int UNKNOWN_HOST_EXCEPTION = 6;
    public static final int BAD_REQUEST_EXCEPTION = 7;

    public static final int PERSISTENCE_COINS_NULL = 1000;
    public static final int PERSISTENCE_COIN_NULL = 1001;
    public static final int PERSISTENCE_HISTORICAL_NULL = 1002;
    public static final int PERSISTENCE_PORTFOLIO_NULL = 1003;

    public static final Info CONNECTION_EXCEPTION_INFO = new Info(CONNECTION_EXCEPTION, "Connexion error");
    public static final Info TIME_OUT_EXCEPTION_INFO = new Info(TIME_OUT_EXCEPTION, "request time out");
    public static final Info UNAUTHORIZED_EXCEPTION_INFO = new Info(UNAUTHORIZED_EXCEPTION, "Request unauthorized");
    public static final Info SERVER_EXCEPTION_INFO = new Info(SERVER_EXCEPTION, "Server error");
    public static final Info GENERIC_EXCEPTION_INFO = new Info(GENERIC_EXCEPTION, "Something went wrong");
    public static final Info NOT_FOUND_EXCEPTION_INFO = new Info(NOT_FOUND_EXCEPTION, "Request not found");
    public static final Info UNKNOWN_HOST_EXCEPTION_INFO = new Info(CONNECTION_EXCEPTION, "Connexion error");
    public static final Info BAD_REQUEST_EXCEPTION_INFO = new Info(BAD_REQUEST_EXCEPTION, "Bad request error");

    public static final Info PERSISTENCE_COINS_NULL_INFO = new Info(PERSISTENCE_COINS_NULL, "Coins null");
    public static final Info PERSISTENCE_COIN_NULL_INFO = new Info(PERSISTENCE_COIN_NULL, "Coin null");
    public static final Info PERSISTENCE_HISTORICAL_NULL_INFO = new Info(PERSISTENCE_HISTORICAL_NULL, "Historical null");
    public static final Info PERSISTENCE_PORTFOLIO_NULL_INFO = new Info(PERSISTENCE_PORTFOLIO_NULL, "Portfolio null");

    private ThrowableManager() {}

    /**
     * Provides info about a throwable.
     *
     * @param throwable the throwable to get info from
     *
     * @return a pair with the id and the error info
     */
    public static Info handleException(Throwable throwable) {
        Info type;
        if (throwable instanceof HttpException) {
            type = handleHttpException(throwable);
        } else if (throwable instanceof UnknownHostException) {
            type = UNKNOWN_HOST_EXCEPTION_INFO;
        } else if (throwable instanceof ConnectException) {
            type = CONNECTION_EXCEPTION_INFO;
        } else if (throwable instanceof SocketTimeoutException) {
            type = TIME_OUT_EXCEPTION_INFO;
        } else if (throwable instanceof PersistenceClientThrowable) {
            type = handlePersistenceException(throwable);
        } else {
            type = GENERIC_EXCEPTION_INFO;
        }
        return type;
    }

    private static Info handleHttpException(Throwable throwable) {
        int code = ((HttpException) throwable).response().code();
        if (code >= 500) {
            return SERVER_EXCEPTION_INFO;
        } else if (code == 400) {
            return BAD_REQUEST_EXCEPTION_INFO;
        } else if (code == 401) {
            return UNAUTHORIZED_EXCEPTION_INFO;
        } else if (code == 404) {
            return NOT_FOUND_EXCEPTION_INFO;
        }
        return GENERIC_EXCEPTION_INFO;
    }

    private static Info handlePersistenceException(Throwable throwable) {
        PersistenceClientThrowable error = (PersistenceClientThrowable) throwable;
        switch (error.getType()) {
            case PersistenceClientThrowable.COINS_NULL:
                return PERSISTENCE_COINS_NULL_INFO;
            case PersistenceClientThrowable.COIN_NULL:
                return PERSISTENCE_COIN_NULL_INFO;
            case PersistenceClientThrowable.HISTORICAL_NULL:
                return PERSISTENCE_HISTORICAL_NULL_INFO;
            case PersistenceClientThrowable.PORTFOLIO_NULL:
                return PERSISTENCE_PORTFOLIO_NULL_INFO;
            default:
                return GENERIC_EXCEPTION_INFO;
        }
    }
}
