package com.example.crm_api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/CRM")
public class HelloResource {
    @GET
    @Path("/hello")@Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @POST
    @Path("/submitTicket") @Produces("application/json")
    public String SubmitATicket ()
    {
        return "Hello, World!";
    }
}
