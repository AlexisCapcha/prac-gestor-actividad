package com.example.actividades_app.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "comentario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comentario_id")
    private Long id;

    @Column(name = "contenido", nullable = false)
    private String contenido;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDate fechaEnvio;

    @Column(name = "hora_envio", nullable = false)
    private LocalTime horaEnvio;

    //FK actividad
    @ManyToOne(optional = false)
    @JoinColumn(
        name = "actividad_id",
        nullable = false,
        unique = false
    )
    private Actividad actividad;
    
    //FK usuario
    @ManyToOne(optional = false)
    @JoinColumn(
        name = "usuario_id",
        nullable = false,
        unique = false
    )
    private Usuario usuario;

    //FK tipo_Comentario
    @ManyToOne(optional = false)
    @JoinColumn(
        name = "tipo_comentario_id",
        nullable = false,
        unique = false
    )
    private TipoComentario tipoComentario;

    //Conexion archivo_Comentario
    @OneToMany(
        mappedBy = "comentario",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ArchivoComentario> archivoComentarios;
}
