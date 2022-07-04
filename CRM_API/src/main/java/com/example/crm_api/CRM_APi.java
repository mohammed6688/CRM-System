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
    @Produces({MediaType.APPLICATION_JSON})
    public Response index() {
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, HEAD")
                .entity("")
                .build();
    }
    @GET
    @Path("/hello")@Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
    @POST
    @Path("/getTicketByID") @Produces("application/json")
    @Consumes("application/json")
    public String getTicketByID (String data) throws SQLException {
        DatabaseManagment DM = new DatabaseManagment();
        Gson gson = new Gson();
        GenericId teamID=gson.fromJson(data,GenericId.class);
        String Result=DM.getTicketById(teamID.ID);
        return Result;
    }
    //agents
    @POST
    @Path("/getOpenTicket") @Produces("application/json")
    @Consumes("application/json")
    public String getOpentTicket (String data) throws SQLException {
        DatabaseManagment DM = new DatabaseManagment();
        Gson gson = new Gson();
        GenericId CutomerID=gson.fromJson(data,GenericId.class);
        String Result=DM.viewOpenTicket(CutomerID.ID);
        return Result;
    }
    //history
    @POST
    @Path("/getTicketHistory") @Produces("application/json")
    @Consumes("application/json")
    public String getTicketHistory (String data) throws SQLException {
        DatabaseManagment DM = new DatabaseManagment();
        Gson gson = new Gson();
        GenericId CutomerID=gson.fromJson(data,GenericId.class);
        String Result=DM.viewTicketHistory(CutomerID.ID);
        return Result;
    }
    // support
    @POST
    @Path("/getTickets") @Produces("application/json")
    @Consumes("application/json")
    public String getTicket (String data) throws SQLException {
        DatabaseManagment DM = new DatabaseManagment();
        Gson gson = new Gson();
        GenericId teamID=gson.fromJson(data,GenericId.class);
        String Result=DM.viewTickets(teamID.ID);
        return Result;
    }
    @POST
    @Path("/submitTicket") @Produces("application/json")
    @Consumes("application/json")
    public String SubmitATicket (String data) throws SQLException {
        DatabaseManagment DM = new DatabaseManagment();
        Gson gson = new Gson();
        Ticket ticket_recieved=gson.fromJson(data,Ticket.class);
        return DM.submitATicket(ticket_recieved);
    }
    @POST
    @Path("/modifyTicket") @Produces("application/json")
    @Consumes("application/json")
    public String modifyTicket (String data) throws SQLException {
        DatabaseManagment DM = new DatabaseManagment();
        Gson gson = new Gson();
        Ticket ticket_recieved=gson.fromJson(data,Ticket.class);
        return DM.modifyATicket(ticket_recieved);
    }

    @POST
    @Path("/Login") @Produces("application/json")
    @Consumes("application/json")
   // @XmlHeader("Access-Control-Allow-Origin")
    public Response checkLogin (String credintials) throws SQLException {
        DBConnection.getCon();
        DatabaseManagment DM = new DatabaseManagment();
        Gson gson = new Gson();
        CredentialsForLogin Clogin=gson.fromJson(credintials,CredentialsForLogin.class);
        System.out.println(Clogin.ID);
        System.out.println(Clogin.password);
        String re =  DM.verfiyLoginForREST(Clogin.ID, Clogin.password);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "POST")
                .entity(re)
                .build();
    }
    @POST
    @Path("/getClassifications") @Produces("application/json")
    @Consumes("application/json")
    public String getClassifications () throws SQLException {
        DatabaseManagment DM = new DatabaseManagment();

        String Result=DM.getClassifications();
        return Result;
    }

    @POST
    @Path("/getType") @Produces("application/json")
    @Consumes("application/json")
    public String getType (String data) throws SQLException {
        DatabaseManagment DM = new DatabaseManagment();
        Gson gson = new Gson();
        GenericId classificationID=gson.fromJson(data,GenericId.class);
        String Result=DM.getType( classificationID.ID);
        return Result;
    }
    @POST
    @Path("/getArea") @Produces("application/json")
    @Consumes("application/json")
    public String getArea (String data) throws SQLException {
        DatabaseManagment DM = new DatabaseManagment();
        Gson gson = new Gson();
        GenericId TypeID=gson.fromJson(data,GenericId.class);
        String Result=DM.getArea( TypeID.ID);
        return Result;
    }

    @POST
    @Path("/getSubArea") @Produces("application/json")
    @Consumes("application/json")
    public String getSubArea (String data) throws SQLException {
        DatabaseManagment DM = new DatabaseManagment();
        Gson gson = new Gson();
        GenericId AreaID=gson.fromJson(data,GenericId.class);
        String Result=DM.getSubArea( AreaID.ID);
        return Result;
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
    class GenericId {
        int ID;

        public GenericId(int ID) {
            this.ID = ID;
        }
    }

