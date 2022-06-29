package com.example.database_management;

public class testmain {
    public static void main (String [] args){
        String Driver = "jdbc:postgresql://";
        String HostName="bqeanzizwzknlsga1loq-postgresql.services.clever-cloud.com";
        String DBName ="bqeanzizwzknlsga1loq";
        String port ="5432";
        String DBUrl=Driver+HostName+":"+port+"/"+DBName;

        String DBUsereName="uirb3exldinkfciyp3su";
        String Password = "CPNJKCLLeaJCxNF5L3CZ";

        DBConnection.createConnection(DBUrl,DBUsereName,Password);
    }
}
