package com.example.database_management;

import java.sql.*;

import com.example.models.Ticket;
import com.google.gson.Gson;
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
}
