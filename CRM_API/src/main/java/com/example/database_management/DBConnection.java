package com.example.database_management;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection con;
    public static Connection createConnection(String dbURL, String dbusername, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
            //   con=DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/ecommerce", dbusername, dbPassword);

            con = DriverManager.getConnection(dbURL, dbusername, dbPassword);

            System.out.println("database connected");
        } catch (Exception ex) {
            System.out.println("exception at database connection" + ex);
        }
        return con;
    }
    public static Connection stablishConnection (){
        String Driver = "jdbc:postgresql://";
        String HostName="bqeanzizwzknlsga1loq-postgresql.services.clever-cloud.com";
        String DBName ="bqeanzizwzknlsga1loq";
        String port ="5432";
        String DBUrl=Driver+HostName+":"+port+"/"+DBName;

        String DBUsereName="uirb3exldinkfciyp3su";
        String Password = "CPNJKCLLeaJCxNF5L3CZ";

        return DBConnection.createConnection(DBUrl,DBUsereName,Password);
    }
    public static Connection getCon (){
        if (con != null){
            return con;
        }else
            return stablishConnection();
    }
    public static Connection stablishBillingConnection (){
        String Driver = "jdbc:postgresql://";
        String HostName="btxnhqatyjxgjkhmqgvg-postgresql.services.clever-cloud.com";
        String DBName ="btxnhqatyjxgjkhmqgvg";
        String port ="5432";
        String DBUrl=Driver+HostName+":"+port+"/"+DBName;

        String DBUsereName="ual1kyfaaahzvalnqmv6";
        String Password = "s4ZDx5MEiWCrXYlDUx1A";

        return DBConnection.createConnection(DBUrl,DBUsereName,Password);
    }
    public static Connection getBillingCon (){
        if (con != null){
            return con;
        }else
            return stablishBillingConnection();
    }
}
