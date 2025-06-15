package org.acme.controllers;

import io.micrometer.core.annotation.Timed;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.acme.dto.reponse.AgifyResponse;
import org.acme.services.AgifyService;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

@ApplicationScoped
@Path("/age")
public class AgeController {

    @Inject
    AgifyService agifyService;

    @GET
    //OpenTelemetry
    @Timed
    @Retry(maxRetries = 3, delay = 500)
    @Timeout(1000)
    @Fallback(fallbackMethod = "fallbackAge")
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.5, delay = 5000)
    public Uni<AgifyResponse> getAge(
           @QueryParam("name") String name
    ) {
        return agifyService.getAge(name);
    }
}
