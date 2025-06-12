package org.acme.clients;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.reponse.AgifyResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "agify-api")
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface AgifyClient {
    @GET
    Uni<AgifyResponse> getAge(@QueryParam("name") String name);
}
