package ru.clevertec.check.dao;

import java.sql.Connection;

public abstract class DaoDB {
    private Connection connection;

    public DaoDB(Connection connection) {
        this.connection = connection;
    }
}
