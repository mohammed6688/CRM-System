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
        Statement sqlStmt = con.createStatement();
        String checkQuery = "select * from employee where ID = '" + id + "'"
                + "and password = '" + password + "'";
        ResultSet rs = sqlStmt.executeQuery(checkQuery);

        if (rs.next()) {
            result = "true";
            teamID = rs.getInt("team_id");
        }
        json.put("login", result);
        json.put("teamID", teamID);
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
        JSONObject json = new JSONObject();
        ArrayList<Ticket> RetriedTickets = new ArrayList<>();
        String result = "false";
        int TicketID = 0 ;
        PreparedStatement stmt = con.prepareStatement("select * from ticket inner join sr_subarea on ticket.sr_id = sr_subarea.id " +
                                                        " inner join sr_area on  sr_subarea.area_id = sr_area.id" +
                                                        " inner join sr_type on  sr_area.type_id = sr_type.id                           " +
                                                        "where sr_type.sr_type = (select category from team where id = ?) ");

        stmt.setInt(1,TeamId);
        ResultSet rs = stmt.executeQuery();

        return rsToJsonArray(rs).toString();
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
}
