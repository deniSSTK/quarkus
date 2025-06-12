package org.acme.services;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.clients.AgifyClient;
import org.acme.dto.reponse.AgifyResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class AgifyService {

    @Inject
    @RestClient
    AgifyClient agifyClient;

    public Uni<AgifyResponse> getAge(String name) {
        return agifyClient.getAge(name);
    }
}
