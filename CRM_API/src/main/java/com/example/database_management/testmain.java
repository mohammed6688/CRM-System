package com.example.database_management;

public class testmain {
    public static void main (String [] args){
//        String Driver = "jdbc:postgresql://";
//        String HostName="btxnhqatyjxgjkhmqgvg-postgresql.services.clever-cloud.com";
//        String DBName ="btxnhqatyjxgjkhmqgvg";
//        String port ="5432";
//        String DBUrl=Driver+HostName+":"+port+"/"+DBName;
//
//        String DBUsereName="ual1kyfaaahzvalnqmv6";
//        String Password = "s4ZDx5MEiWCrXYlDUx1A";
//
//        returnonnection.createConnection(DBUrl,DBUsereName,Password);
        DBConnection.getBillingCon();
    }
}
