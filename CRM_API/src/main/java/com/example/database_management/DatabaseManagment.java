package com.example.database_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONObject;


public class DatabaseManagment {
    private Connection con;

    public DatabaseManagment() {
        con=DBConnection.getCon();
    }
    public String verfiyLoginForREST(int id, String password) throws SQLException {
        JSONObject json = new JSONObject();
        String result = "false";
        Statement sqlStmt = con.createStatement();
        String checkQuery = "select * from employee where ID = '" + id + "'"
                + "and password = '" + password + "'";
        ResultSet rs = sqlStmt.executeQuery(checkQuery);

        if (rs.next()) {
            result = "true";
        }

        return json.put("login", result).toString();
    }
}
