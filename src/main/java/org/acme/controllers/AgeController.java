package org.acme.controllers;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.acme.dto.reponse.AgifyResponse;
import org.acme.services.AgifyService;

@Path("/age")
public class AgeController {

    @Inject
    AgifyService agifyService;

    //Doesn't work yet
    @GET
    public Uni<AgifyResponse> getAge(
           @QueryParam("name") String name
    ) {
        return agifyService.getAge(name);
    }
}
