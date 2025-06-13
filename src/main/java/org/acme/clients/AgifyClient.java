package org.acme.clients;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.acme.dto.reponse.AgifyResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "agify-api")
@Path("/")
public interface AgifyClient {
    @GET
    Uni<AgifyResponse> getAge(@QueryParam("name") String name);
}
