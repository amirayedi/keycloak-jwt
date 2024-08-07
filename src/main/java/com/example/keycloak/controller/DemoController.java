package com.example.keycloak.controller;


import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class DemoController {


    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint";
    }

    @Secured("ROLE_USER")
    @GetMapping("/secure")
    public String secureEndpoint() {
        return "This is a secure endpoint";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public String adminEndpoint() {
        return "This is an admin endpoint";
    }
}
