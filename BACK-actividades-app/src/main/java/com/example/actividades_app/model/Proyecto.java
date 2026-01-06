package com.example.actividades_app.model;

import com.example.actividades_app.enums.Estado;
import com.example.actividades_app.model.relation.ProyectoUsuario;

import java.time.LocalDate;
import java.util.List;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proyecto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proyecto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proyecto_id")
    private long id;

    @Column(name = "titulo_Proyecto", nullable = false, unique = true)
    private String titulo;

    //FK area
    @ManyToOne(optional = false)
    @JoinColumn(
        name = "area_id", 
        nullable = false,
        unique = false
    )
    private Area area;

    @Column(name = "fecha_creacion_proyecto", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "fecha_finalizacion_proyecto", nullable = false)
    private LocalDate fechaFinalizacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_proyecto", nullable = false)
    private Estado estado;

    //Conexion ProyectoUsuario
    @OneToMany(
        mappedBy = "proyecto",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    private List<ProyectoUsuario> usuariosAsignados;
    
    //Conexion Actividades
    @OneToMany(
        mappedBy = "proyecto",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    private List<Actividad> actividades;

}
