package com.example.actividades_app.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.actividades_app.dto.RegisterRequestDTO;
import com.example.actividades_app.repository.UsuarioRepository;
import com.example.actividades_app.model.Usuario;
import com.example.actividades_app.model.Rol;
import com.example.actividades_app.repository.RolRepository;

@RestController
public class PrincipalController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @GetMapping("/hola")
    public String hola() {
        return "Hola, No seguro.";
    }

    @GetMapping("/hola-seguro")
    public String holaSeguro(@RequestParam String nombre) {
        return "Hola, Has accedido a una ruta segura.";
    }

    @PostMapping("/Registrar")
    public ResponseEntity<?> RegisterRequest(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {

        Set<Rol> roles = registerRequestDTO.getRoles().stream()
                .map(roleStr -> rolRepository.findByName(roleStr)
                        .orElseGet(() -> rolRepository.save(Rol.builder().name(roleStr).build()))
                )
                .collect(Collectors.toSet());

        Usuario nuevoUsuario = Usuario.builder()
                .email(registerRequestDTO.getEmail())
                .username(registerRequestDTO.getUsername())
                .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                .roles(roles)
                .build();
        usuarioRepository.save(nuevoUsuario);

        return ResponseEntity.ok(nuevoUsuario);
        
    }

    @DeleteMapping("/EliminarUsuario")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String EliminarUsuario(@RequestParam String id) {
        usuarioRepository.deleteById(Long.parseLong(id));;
        return "Usuario eliminado id: ".concat(id);
    }

}
