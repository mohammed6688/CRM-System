package com.example.crm_api;

import com.example.database_management.DBConnection;
import com.example.database_management.DatabaseManagment;
import com.example.models.Ticket;
import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.message.XmlHeader;

import java.sql.SQLException;

//@ApplicationPath("/api")

@Path("/CRM")
public class CRM_APi {
    @POST
    @Path("/test")
    @Produces({MediaType.TEXT_PLAIN})
    public Response index() {
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity("")
                .build();
    }
    @GET
    @Path("/hello")@Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
    //agents
    @GET
    @Path("/getOpenTicket") @Produces("application/json")
    public String getOpentTicket (String data) throws SQLException {
        DatabaseManagment DM = new DatabaseManagment();
        Gson gson = new Gson();
        IdForTeam teamID=gson.fromJson(data,IdForTeam.class);
        String Result=DM.viewOpenTicket(teamID.ID);
        return Result;
    }
    //history
    @GET
    @Path("/getTicketHistory") @Produces("application/json")
    public String getTicketHistory (String data) throws SQLException {
        DatabaseManagment DM = new DatabaseManagment();
        Gson gson = new Gson();
        IdForTeam teamID=gson.fromJson(data,IdForTeam.class);
        String Result=DM.viewOpenTicket(teamID.ID);
        return Result;
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
    @Consumes("application/json")
   // @XmlHeader("Access-Control-Allow-Origin")
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

