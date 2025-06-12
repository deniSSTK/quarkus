package org.acme.controllers;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.request.UserRequest;
import org.acme.entities.User;
import org.acme.security.BCryptMechanism;
import org.acme.services.UserService;
import java.util.List;

@ApplicationScoped
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    public Uni<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @WithTransaction
    @POST
    public Uni<User> createUser(UserRequest req) {
        String passwordHash = BCryptMechanism.cachePassword(req.password);
        User user = new User(req.name, req.email, passwordHash);
        return userService.createUser(user);
    }

    @GET
    @Path("/get-names")
    public Uni<List<String>> getAllUserNames() {
        return userService.getAllUserNames();
    }

    @GET
    @Path("/get-name/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> getUserName(@PathParam("id") Integer id) {
        return userService.getUserName(id);
    }

    @GET
    @Path("/change-user-role/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> changeUserRole(
            @PathParam("id") Integer id,
            @QueryParam("role") String role
    ) {
        return userService.changeUserRole(id, role);
    }
}