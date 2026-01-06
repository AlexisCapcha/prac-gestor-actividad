package com.example.actividades_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "archivo_comentario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArchivoComentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archivo_comentario_id")
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "formato", nullable = false)
    private String formato;

    //FK Comentario
    @ManyToOne(optional = false)
    @JoinColumn(
        name = "comentario_id",
        nullable = false,
        unique = false
    )
    private Comentario comentario;

}
