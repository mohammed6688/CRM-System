package com.example.database_management;

import java.sql.*;
import java.util.ArrayList;

import com.example.models.Ticket;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;


public class DatabaseManagment {
    private Connection con;

    public DatabaseManagment() {
        con=DBConnection.getCon();
    }
    public String verfiyLoginForREST(int id, String password) throws SQLException {
        JSONObject json = new JSONObject();
        String result = "false";
        int teamID =-1;
        String level ="-1";
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
        json.put("level",level);
        return json.toString();

    }
    public String submitATicket (Ticket TicketRecieved) throws SQLException {
        JSONObject json = new JSONObject();
        String result = "false";
        int TicketID = 0 ;
        PreparedStatement stmt = con.prepareStatement("insert into ticket (description , customer_id ,emp_id_for_creation ,sr_id)" +
                                                        " values (? , ? , ? , ? ) RETURNING ID");
        stmt.setString(1, TicketRecieved.getDescription());
        stmt.setInt(2,TicketRecieved.getCustomer_id());
        stmt.setInt(3,TicketRecieved.getEmp_id_for_creation());
        stmt.setInt(4,TicketRecieved.getSr_id());

        ResultSet checkquery = stmt.executeQuery();
        if (checkquery.next())
        {
            TicketID = checkquery.getInt("ID");
        }
        return json.put("TicketID",TicketID).toString();
    }

    public String viewTickets (int TeamId) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("select * from ticket inner join sr_subarea on ticket.sr_id = sr_subarea.id " +
                                                        " inner join sr_area on  sr_subarea.area_id = sr_area.id" +
                                                        " inner join sr_type on  sr_area.type_id = sr_type.id                           " +
                                                        "where sr_type.sr_type = (select category from team where id = ?) ");

        stmt.setInt(1,TeamId);
        ResultSet rs = stmt.executeQuery();
        String result =rsToJsonArray(rs).toString();
        return result;
    }
    public String viewOpenTicket (int CustomerId) throws SQLException {
        JSONObject json = new JSONObject();
        int TicketID = 0 ;
        PreparedStatement stmt = con.prepareStatement("select * from ticket where customer_id = ? and status = 'open' ");

        stmt.setInt(1,CustomerId);
        ResultSet rs = stmt.executeQuery();

        String result =rsToJson(rs).toString();
        return result;
    }
    private JSONArray rsToJsonArray (ResultSet rs) throws SQLException {

        JSONArray jsonArray = new JSONArray();

        while (rs.next()) {

            int columns = rs.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();

            for (int i = 0; i < columns; i++)
                obj.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getObject(i + 1));

            jsonArray.put(obj);
        }
        return jsonArray;
    }
    private JSONObject rsToJson (ResultSet rs) throws SQLException {
        JSONObject obj = null;
        while (rs.next()) {
            int columns = rs.getMetaData().getColumnCount();
                obj = new JSONObject();
            for (int i = 0; i < columns; i++)
                obj.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getObject(i + 1));
        }
        return obj;
    }
}
