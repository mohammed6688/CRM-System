package com.example.database_management;

import java.sql.SQLException;

public class testmain {
    public static void main (String [] args) throws SQLException {
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
  //      DBConnection.getBillingCon();
    //    DatabaseManagment DM =new DatabaseManagment();
//        try {
    //        System.out.println((String) DM.getEmailandMSISDN(2).get("email"));
  //         System.out.println((String) DM.getEmailandMSISDN(2).get("msisdn"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
  //    SMS.startTicket((String) DM.getEmailandMSISDN(2).get("msisdn"),1,"Hello");
      // SMS.startTicket("+201100081688",1,"Hello");
        Email.sendemail("CRM team ","eng.abdelrahman.mostafa.soliman@gmail.com");
    }
}
