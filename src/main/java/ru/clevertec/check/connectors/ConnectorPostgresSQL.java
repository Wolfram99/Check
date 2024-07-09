package ru.clevertec.check.connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorPostgresSQL implements ConnectionToDB{
    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;

    public ConnectorPostgresSQL(String URL, String USERNAME, String PASSWORD) {
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    @Override
    public Connection openDBConnection() {
        try {

            Class.forName("org.postgresql.Driver");
            System.out.println("**** Loaded the JDBC driver");
            Connection connection = DriverManager.getConnection( URL,
                                                                USERNAME,
                                                                PASSWORD);
            System.out.println("**** Created a JDBC connection to the data source");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("**** Failed to create a database connection");
        }
        return null;
    }

    @Override
    public void closeDBConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("**** Failed to close database connection");
        }
    }

}
