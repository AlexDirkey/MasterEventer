package org.example.mastereventer.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final String URL = "jdbc:sqlserver://10.176.111.34:1433;databaseName=MasterEventer;encrypt=false;trustServerCertificate=true;";
    private static final String USER = "CSe2024a_e_0";     // brugernavn
    private static final String PASSWORD = "CSe2024aE0!24";  // adgangskode

    public static Connection getConnection() {
        try {
            // ("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }
}

