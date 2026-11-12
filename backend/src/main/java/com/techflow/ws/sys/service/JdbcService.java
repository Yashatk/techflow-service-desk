package com.techflow.ws.sys.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcService {

    public static Connection connect(String connectionString, String user, String password) throws SQLException {
        return DriverManager.getConnection(connectionString, user, password);
    }

    public static boolean isConnected(Connection connection) {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public static ResultSet executeSelect(Connection connection, String sql) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Connection is null or closed");
        }
        return connection.createStatement().executeQuery(sql);
    }

    public static int executeUpdate(Connection connection, String sql) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Connection is null or closed");
        }
        return connection.createStatement().executeUpdate(sql);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Log the exception or handle it as needed
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // Log the exception or handle it as needed
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                // Log the exception or handle it as needed
            }
        }
    }
    
}
