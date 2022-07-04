package com.example.database_management;

import java.sql.*;

import com.example.models.Ticket;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.json.JSONArray;
import org.json.JSONObject;

public class DatabaseManagment {

    private final Connection con;
    public static String fileNamePdf, to, Description, phone;
    public static int TicketID;

    public DatabaseManagment() {
        con = DBConnection.getCon();
    }

    public String verfiyLoginForREST(int id, String password) throws SQLException {
        JSONObject json = new JSONObject();
        String result = "false";
        int teamID = -1;
        String level = "-1";
        Statement sqlStmt = con.createStatement();
        String checkQuery = "select e.team_id, t.level from employee e , team t where t.id = e.team_id and e.ID = '" + id + "'"
                + "and e.password = '" + password + "'";
        ResultSet rs = sqlStmt.executeQuery(checkQuery);

        if (rs.next()) {
            result = "true";
            teamID = rs.getInt("team_id");
            level = rs.getString("level");
        }
        json.put("login", result);
        json.put("teamID", teamID);
        json.put("level", level);
        return json.toString();

    }

    public String submitATicket(Ticket TicketRecieved) throws SQLException {
        JSONObject json = new JSONObject();

        PreparedStatement stmt = con.prepareStatement("insert into ticket (description , customer_id ,emp_id_for_creation ,sr_id)"
                + " values (? , ? , ? , ? ) RETURNING ID");
        stmt.setString(1, TicketRecieved.getDescription());
        stmt.setInt(2, TicketRecieved.getCustomer_id());
        stmt.setInt(3, TicketRecieved.getEmp_id_for_creation());
        stmt.setInt(4, TicketRecieved.getSr_id());

        ResultSet checkquery = stmt.executeQuery();
        if (checkquery.next()) {
            TicketID = checkquery.getInt("ID");
            sendemail("Dear customer, we would like to inform you that your request has been"
                    + " submitted with the number " + TicketID
                    + " submitted regarding " + Description
                    + " and the problem is being resolved within 48 working hours", to);
            SMS.startTicket(phone, TicketID, Description);

        }
        return json.put("TicketID", TicketID).toString();

    }

    public String viewTickets(int TeamId) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("select * from ticket inner join sr_subarea on ticket.sr_id = sr_subarea.id "
                + " inner join sr_area on  sr_subarea.area_id = sr_area.id"
                + " inner join sr_type on  sr_area.type_id = sr_type.id                           "
                + "where sr_type.sr_type = (select category from team where id = ?) ");

        stmt.setInt(1, TeamId);
        ResultSet rs = stmt.executeQuery();
        String result = rsToJsonArray(rs).toString();
        return result;
    }

    public String viewOpenTicket(int CustomerId) throws SQLException {
//        JSONObject json = new JSONObject();
        PreparedStatement stmt = con.prepareStatement("select * from ticket where customer_id = ? and status = 'open' ");

        stmt.setInt(1, CustomerId);
        ResultSet rs = stmt.executeQuery();

        String result = rsToJson(rs).toString();
        return result;
    }

    private JSONArray rsToJsonArray(ResultSet rs) throws SQLException {

        JSONArray jsonArray = new JSONArray();

        while (rs.next()) {

            int columns = rs.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();

            for (int i = 0; i < columns; i++) {
                obj.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getObject(i + 1));
            }

            jsonArray.put(obj);
        }
        return jsonArray;
    }

    private JSONObject rsToJson(ResultSet rs) throws SQLException {
        JSONObject obj = null;
        while (rs.next()) {
            int columns = rs.getMetaData().getColumnCount();
            obj = new JSONObject();
            for (int i = 0; i < columns; i++) {
                obj.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getObject(i + 1));
            }
        }
        return obj;
    }

    public void sendemail(String text, String to) {

        // Sender's email ID needs to be mentioned
        String from = "project.billingiti@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass 
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("project.billingiti@gmail.com", "123456789billing");

            }

        });
        //session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Vodafone CRM Team");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {

                attachmentPart.attachFile(fileNamePdf);
                textPart.setText(text);
//                "Dear customer, we would like to inform you that your request has been"
//                        + " submitted with the number "+TicketID
//                        +" submitted regarding "+Description
//                        +" and the problem is being resolved within 48 working hours");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {
            }

            message.setContent(multipart);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
        }
    }

}
