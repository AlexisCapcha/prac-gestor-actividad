package com.example.actividades_app.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_comentario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoComentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_comentario_id")

    private Long id;

    @Column(name = "nombre_tipo", nullable= false, unique = true)
    private String nombreTipo;

    //Conexion Comentario
    @OneToMany(
        mappedBy = "tipoComentario"
    )
    private List<Comentario> comentarios;
}
