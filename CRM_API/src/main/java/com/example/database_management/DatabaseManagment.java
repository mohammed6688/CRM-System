package com.example.database_management;

import java.sql.*;

import com.example.models.Ticket;
import org.json.JSONArray;
import org.json.JSONObject;

public class DatabaseManagment {

    private  Connection con;
    private  Connection Billingcon;

    public static String  to , Description, phone;
    public static int TicketID;

    public DatabaseManagment() {
        con = DBConnection.getCon();
        Billingcon = DBConnection.getBillingCon();
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
        to= (String) getEmailandMSISDN(TicketRecieved.getCustomer_id()).get("email");
        phone= (String) getEmailandMSISDN(TicketRecieved.getCustomer_id()).get("msisdn");
        PreparedStatement stmt = con.prepareStatement("insert into ticket (description , customer_id ,emp_id_for_creation ,sr_id)"
                + " values (? , ? , ? , ? ) RETURNING ID");
        stmt.setString(1, TicketRecieved.getDescription());
        stmt.setInt(2, TicketRecieved.getCustomer_id());
        stmt.setInt(3, TicketRecieved.getEmp_id_for_creation());
        stmt.setInt(4, TicketRecieved.getSr_id());

        ResultSet checkquery = stmt.executeQuery();
        Description = TicketRecieved.getDescription();
        if (checkquery.next()) {
            TicketID = checkquery.getInt("ID");
//            Email.sendemail("Dear customer, we would like to inform you that your request has been"
//                    + " submitted with the number " + TicketID
//                    + " submitted regarding " + Description
//                    + " and the problem is being resolved within 48 working hours", to);
//            SMS.startTicket(phone, TicketID, Description);

        }
        return json.put("TicketID", TicketID).toString();

    }
    public String getTicketById (int TicketId) throws SQLException {
        System.out.println(TicketId);
        PreparedStatement stmt = con.prepareStatement("select * from ticket where id = ? ");
        stmt.setInt(1, TicketId);
        ResultSet rs = stmt.executeQuery();
        String result = rsToJson(rs).toString();
        return result;
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

    public String  modifyATicket (Ticket ticket) throws SQLException {
        JSONObject json = new JSONObject();
        to= (String) getEmailandMSISDN(ticket.getCustomer_id()).get("email");
        phone= (String) getEmailandMSISDN(ticket.getCustomer_id()).get("msisdn");
        PreparedStatement stmt = con.prepareStatement( "update ticket SET " +
                "status=?," +
                "description=?," +
                "emp_id_for_management=?," +
                "customer_notification=?," +
                "is_notified=?," +
                "notfication_detailes=?," +
                "sr_id=?  RETURNING ID");
        Description = ticket.getDescription();
        stmt.setString(1,ticket.getStatus());
        stmt.setString(2,ticket.getDescription());
        stmt.setInt(3,ticket.getEmp_id_for_management());
        stmt.setBoolean(4,ticket.isCustomer_notification());
        stmt.setBoolean(5,ticket.isIs_notified());
        stmt.setString(6,ticket.getNotfication_detailes());
        stmt.setInt(7,ticket.getSr_id());
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            TicketID = rs.getInt("ID");
            if (ticket.getStatus().equalsIgnoreCase("closed")){
                //send notifcation to the customer
                Email.sendemail("Dear customer, we would like to inform you that your request "
                    + "with the number " + TicketID
                    + " regarding " + Description
                    + " and the problem is being solved.", to);
            SMS.endTicket(phone, TicketID, Description);
                
            }
        }
        return json.put("TicketID", TicketID).toString();
    }
    public String viewOpenTicket(int CustomerId) throws SQLException {

        PreparedStatement stmt = con.prepareStatement("select * from ticket where customer_id = ? and status = 'open' ");

        stmt.setInt(1, CustomerId);
        ResultSet rs = stmt.executeQuery();

        String result = rsToJsonArray(rs).toString();
        return result;
    }
    public String viewTicketHistory (int CustomerId) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("select * from history where customer_id = ?  ");
        stmt.setInt(1, CustomerId);
        ResultSet rs = stmt.executeQuery();
        String result = rsToJsonArray(rs).toString();
        return result;
    }
    public String getClassifications() throws SQLException {
        PreparedStatement stmt = con.prepareStatement("select * from sr_classification ");
        ResultSet rs = stmt.executeQuery();
        String result = rsToJsonArray(rs).toString();
        return result;
    }
    public String getType(int classificationID) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("select * from sr_type where classification_id = ?");
        stmt.setInt(1, classificationID);
        ResultSet rs = stmt.executeQuery();
        String result = rsToJsonArray(rs).toString();
        return result;
    }
    public String getArea(int TypeID) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("select * from sr_area where type_id = ?");
        stmt.setInt(1, TypeID);
        ResultSet rs = stmt.executeQuery();
        String result = rsToJsonArray(rs).toString();
        return result;
    }
    public String getSubArea(int AreaID) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("select * from sr_subarea where area_id = ?");
        stmt.setInt(1, AreaID);
        ResultSet rs = stmt.executeQuery();
        String result = rsToJsonArray(rs).toString();
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
    private JSONObject getEmailandMSISDN (int customer_ID) throws SQLException {
        String email =null;
        JSONObject jsonObject =new JSONObject();
        PreparedStatement stmt = Billingcon.prepareStatement("select cr.email,co.msisdn from contract as co inner join customer as cr on co.cu_id = cr.cu_id where co.con_id = ?  ");
        stmt.setInt(1, customer_ID);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            jsonObject.put("email",rs.getString("email"));
            jsonObject.put("msisdn",rs.getString("msisdn"));
        }
        return jsonObject;
    }

   
}
