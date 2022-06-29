package com.example.database_management;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection con;
    public static void createConnection(String dbURL, String dbusername, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
            //   con=DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/ecommerce", dbusername, dbPassword);

            con = DriverManager.getConnection(dbURL, dbusername, dbPassword);

            System.out.println("database connected");
        } catch (Exception ex) {
            System.out.println("exception at database connection" + ex);
        }
    }
}
