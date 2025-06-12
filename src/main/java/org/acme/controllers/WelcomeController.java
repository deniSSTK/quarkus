package org.acme.controllers;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WelcomeController {
    public String welcome() {
        return "Welcome to JAVA";
    }

    public String welcomeByName(String name) {
        return String.format("Welcome %s!", name);
    }
}