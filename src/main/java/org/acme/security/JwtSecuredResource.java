package org.acme.security;

import io.micrometer.core.annotation.Timed;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/secure")
public class JwtSecuredResource {

    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed("user")
    public String secured() {
      return "Hello " + jwt.getName() + ", your role: " + jwt.getGroups();
    }

    @GET
    @Path("/raw")
    public String rawToken() {
        return jwt.getRawToken();
    }
}
