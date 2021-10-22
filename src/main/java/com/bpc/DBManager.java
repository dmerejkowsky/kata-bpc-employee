package com.bpc;

import java.sql.*;

public class DBManager {

    private Connection connection;
    private String url = "jdbc:derby://localhost:1527/bpc";
    private String user = "adm";
    private String password = "adm";
    private boolean isDebug = true;

    public DBManager() {
        try {
            if (!isDebug)
                connection = DriverManager.getConnection(url);
            else
                connection = DriverManager.getConnection("jdbc:derby://localhost:1527/bpc_debug");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
