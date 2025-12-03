package com.example.actividades_app.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class RolesController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/accessadmin")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String accesoAmin(){
        return "Hola Admin";
    } 

    @GetMapping("/accessuser")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public String accesouser(){
        return "Hola User";
    }
}
