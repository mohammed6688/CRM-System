package com.example.crm_api;

import com.example.database_management.DBConnection;
import com.example.database_management.DatabaseManagment;
import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.sql.SQLException;

@Path("/CRM")
public class HelloResource {
    @GET
    @Path("/hello")@Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @POST
    @Path("/submitTicket") @Produces("application/json")
    public String SubmitATicket (String credintials) throws SQLException {
        DBConnection.getCon();
        DatabaseManagment DM = new DatabaseManagment();
        Gson gson = new Gson();
        CredentialsForLogin Clogin=gson.fromJson(credintials,CredentialsForLogin.class);
        System.out.println(Clogin.ID);
        System.out.println(Clogin.password);
        return DM.verfiyLoginForREST(Clogin.ID, Clogin.password);
    }
    @POST
    @Path("/Login") @Produces("application/json")
    public String checkLogin (String credintials) throws SQLException {
        DBConnection.getCon();
        DatabaseManagment DM = new DatabaseManagment();
        Gson gson = new Gson();
        CredentialsForLogin Clogin=gson.fromJson(credintials,CredentialsForLogin.class);
        System.out.println(Clogin.ID);
        System.out.println(Clogin.password);
        return DM.verfiyLoginForREST(Clogin.ID, Clogin.password);
    }
}
class CredentialsForLogin {
    int ID;
    String password;

    public CredentialsForLogin(int ID, String password) {
        this.ID = ID;
        this.password = password;
    }
}