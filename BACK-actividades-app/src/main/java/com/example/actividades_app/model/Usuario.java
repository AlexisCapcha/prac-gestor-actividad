package com.example.actividades_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Set;

import com.example.actividades_app.model.relation.ProyectoUsuario;
import com.example.actividades_app.model.relation.UsuarioActividad;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Email
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;
    
    //Conexion con tabla intermedio
    @ManyToMany(fetch = FetchType.EAGER,targetEntity = Rol.class, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "usuario_rol",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles;

    //Conexion ProyectoUsuario
    @OneToMany(
        mappedBy = "usuario",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    private List<ProyectoUsuario> proyectos;

    //Conexion UsuarioActividad
    @OneToMany(
        mappedBy = "usuario",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    private List<UsuarioActividad> actividades;

    //Conexion comentario
    @OneToMany(
        mappedBy = "usuario",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    private List<Comentario> comentarios;
}
