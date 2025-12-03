package com.example.actividades_app.service.Impl;

import com.example.actividades_app.model.Usuario;
import com.example.actividades_app.service.UsuarioService;
import com.example.actividades_app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario registrar(Usuario usuario) {
        
        //RN evitar duplicados usuarios
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new RuntimeException("El username  ya existe");    
        } 
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }
}
