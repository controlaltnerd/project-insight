//this class is for login into the database
package com.assemblers.app.DatabaseAccess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseAccessHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/projectCSC3350";
    private static final String USER = "root";
    private static final String PASSWORD = "M@x1800?"; //this is YOUR SQL SEVER PASSWORD

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }
}
