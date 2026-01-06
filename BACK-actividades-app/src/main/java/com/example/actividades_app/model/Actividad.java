package com.example.actividades_app.model;

import java.util.List;

import com.example.actividades_app.enums.Estado;
import com.example.actividades_app.model.relation.UsuarioActividad;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "actividad")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actividad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actividad_id")
    private Long id;

    @Column(name = "titulo_actividad", nullable = false, unique = false)
    private String titulo;

    @Column(name = "descripcion_actividad", nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_actividad", nullable = false)
    private Estado estado;

    //FK proyecto
    @ManyToOne(optional = false)
    @JoinColumn(
        name = "proyecto_id",
        nullable = false,
        unique = false
    )
    private Proyecto proyecto;

    //Conexion UsuarioActividad
    @OneToMany(
        mappedBy = "actividad",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<UsuarioActividad> usuariosAsignados;

    //Conexion comentario
    @OneToMany(
        mappedBy = "actividad",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    private List<Comentario> comentarios;
}
