package com.example.keycloak.controller;

import com.example.keycloak.dto.UserDto;
import com.example.keycloak.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final KeycloakService keycloakUserService;

    @Autowired
    public UserController(KeycloakService keycloakUserService) {
        this.keycloakUserService = keycloakUserService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        Response response = keycloakUserService.createUser(
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPassword()
        );

        if (response.getStatus() == 201) {
            return ResponseEntity.status(201).body("User created successfully.");
        } else {
            return ResponseEntity.status(response.getStatus()).body("Error creating user.");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserRepresentation>> listUsers() {
        List<UserRepresentation> users = keycloakUserService.listUsers();
        return ResponseEntity.ok(users);
    }
}