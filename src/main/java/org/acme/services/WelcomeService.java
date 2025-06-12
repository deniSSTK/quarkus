package org.acme.services;

import io.quarkus.vertx.web.Route;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.controllers.WelcomeController;

@ApplicationScoped
public class WelcomeService {

    @Inject
    WelcomeController welcomeService;

    @Route(
            path = "/welcome",
            methods = { Route.HttpMethod.GET }
    ) Uni<String> hello() {
        return Uni.createFrom().item(welcomeService.welcome());
    }

    @Route(
            path = "/welcome/:name",
            methods = { Route.HttpMethod.GET },
            type = Route.HandlerType.BLOCKING
    ) Uni<String> helloName(RoutingContext ctx) {
        String name = ctx.pathParam("name");
        return Uni.createFrom().item(welcomeService.welcomeByName(name));
    }
}