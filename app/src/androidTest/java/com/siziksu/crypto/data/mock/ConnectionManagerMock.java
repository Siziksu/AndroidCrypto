package com.siziksu.crypto.data.mock;

import com.siziksu.crypto.common.managers.ConnectionManagerContract;
import com.siziksu.crypto.common.managers.model.Connection;

public class ConnectionManagerMock implements ConnectionManagerContract {

    @Override
    public Connection getConnection() {
        return new Connection(true, "WIFI");
    }
}
