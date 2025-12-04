package com.example.actividades_app.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class RolesController {

    @GetMapping("/accessadmin")
    @PreAuthorize("hasRole('ADMIN')")   
    public String accessAdmin() {
        return "Bienvenido, tienes acceso de administrador.";
    }

    @GetMapping("/accessuser")
    @PreAuthorize("hasRole('USER')")    
    public String accessUser() {
        return "Bienvenido, tienes acceso de Usuario.";
    }
}
