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

    public static final String ACCOUNT_SID = "AC5136320a729813730fe3a47285a31886";
    public static final String AUTH_TOKEN = "d81d293af4a29be85cc469f4041edfae";

    public void sendSMS(String num, int tck, String text) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(num),
                new com.twilio.type.PhoneNumber("+16073035175"),
                "Dear customer, we would like to inform you that your request has been submitted with the number "+tck
                        +" submitted regarding "+text
                        +" and the problem is being resolved within 48 working hours")
                .create();

        System.out.println(message.getSid());
    }
}
