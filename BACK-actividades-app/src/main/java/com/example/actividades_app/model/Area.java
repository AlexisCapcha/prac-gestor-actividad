package com.example.actividades_app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "area")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long id;

    @Column(name = "nombre_area", nullable = false, unique = true)
    private String nombreArea;

    @Column(name = "descripcion_area", nullable = false, unique = false)
    private String descripcionArea;

    //Conexion Proyecto
    @OneToMany(
        mappedBy = "area",
        cascade = CascadeType.ALL,
        orphanRemoval = false
        )
    private List<Proyecto> proyectos;

    
}