package com.siziksu.crypto.common.managers;

import com.siziksu.crypto.common.managers.model.Connection;

public interface ConnectionManagerContract {

    @SuppressWarnings("deprecation")
    Connection getConnection();
}
