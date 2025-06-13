package org.acme.controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/admin")
public class AdminResource {

    @GET
    @RolesAllowed("admin")
    public String secureEndpoint() {
        return "Welcome, admin!";
    }

    @GET
    @Path("/public")
    public String openEndpoint() {
        return "Public page";
    }
}
