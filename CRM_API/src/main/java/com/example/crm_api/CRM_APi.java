package com.example.crm_api;

import com.example.database_management.DBConnection;
import com.example.database_management.DatabaseManagment;
import com.example.models.Ticket;
import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.sql.SQLException;

@Path("/CRM")
public class CRM_APi {
    @GET
    @Path("/hello")@Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    // support
    @GET
    @Path("/getTickets") @Produces("application/json")
    public String getTicket (String data) throws SQLException {
        DatabaseManagment DM = new DatabaseManagment();
        Gson gson = new Gson();
        IdForTeam teamID=gson.fromJson(data,IdForTeam.class);
        String Result=DM.viewTickets(teamID.ID);
        return Result;
    }
    @POST
    @Path("/submitTicket") @Produces("application/json")
    public String SubmitATicket (String data) throws SQLException {
        DatabaseManagment DM = new DatabaseManagment();
        Gson gson = new Gson();
        Ticket ticket_recieved=gson.fromJson(data,Ticket.class);
        return DM.submitATicket(ticket_recieved);
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
    class IdForTeam {
        int ID;

        public IdForTeam(int ID) {
            this.ID = ID;
        }
    }

