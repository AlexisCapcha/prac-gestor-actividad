package com.example.actividades_app.service;

import com.example.actividades_app.model.Rol;
import java.util.Optional;
import java.util.List;

public interface RolService{
    Rol registrar(Rol rol);
    Optional<Rol> buscarPorNombre(String name);
    List<Rol> obtenerTodosLosRoles();
}
