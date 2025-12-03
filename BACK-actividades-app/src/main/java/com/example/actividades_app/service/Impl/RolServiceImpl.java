package com.example.actividades_app.service.Impl;

import com.example.actividades_app.model.Rol;
import com.example.actividades_app.service.RolService;
import com.example.actividades_app.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.List;

public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol registrar(Rol rol) {
        //RN evitar duplicados roles
        if (rolRepository.existsByName(rol.getName())) {
            throw new RuntimeException("El rol ya existe");
        }
        return rolRepository.save(rol);
    }

    @Override
    public Optional<Rol> buscarPorNombre(String name) {
        return rolRepository.findByName(name);
    }

    @Override
    public List<Rol> obtenerTodosLosRoles() {
        return rolRepository.findAll();
    }
}
