/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.database_management;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

/**
 *
 * @author Mustafa Raed
 */
public class SMS {

    private static final String ACCOUNT_SID = "AC5136320a729813730fe3a47285a31886";
    private static final String AUTH_TOKEN = "94d113b76801cdd{delete}b6de2b6b60723601e";
    private static final String MY_PHONE = "+12055468704";

    public static void startTicket(String num, int tck, String text) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(num),
                new com.twilio.type.PhoneNumber(MY_PHONE),
                "Dear customer, we would like to inform you that your request has been submitted with the number " + tck
                + " submitted regarding " + text
                + " and the problem is being resolved within 48 working hours")
                .create();

        System.out.println(message.getSid());
    }

    public static void endTicket(String num, int tck, String text) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(num),
                new com.twilio.type.PhoneNumber(MY_PHONE),
                "Dear customer, we would like to inform you that your request with ticket id:"+tck
                + " has been solved, for the following issue:  " + text
                + " your satisfaction is our priority")
                .create();

        System.out.println(message.getSid());
    }
}
