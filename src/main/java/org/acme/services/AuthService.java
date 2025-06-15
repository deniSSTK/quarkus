package org.acme.services;

import io.smallrye.jwt.build.Jwt;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.time.Duration;
import java.util.Set;

@Path("/auth")
public class AuthService {

    @POST
    @Path("/login")
    public Response login() {
        String token = Jwt.issuer("example-issuer")
                .subject("denis")
                .groups(Set.of("user"))
                .expiresIn(Duration.ofHours(1))
                .sign();

        return Response.ok(token).build();
    }
}

