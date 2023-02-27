package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Data
@Table(name = "bitacora")
public class Bitacora {
    @Id
    @Column(name = "id_bitacora", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tarea", nullable = false, referencedColumnName = "id_tarea")
    private Tarea idTarea;

    @Lob
    @Column(name = "descripcion_bitacora", nullable = false)
    private String descripcionBitacora;

    @Lob
    @Column(name = "observacion_bitacora")
    private String observacionBitacora;

    @Column(name = "fecha_hora", nullable = false)
    private Instant fechaHora;

    @Column(name = "file_foto", nullable = false)
    private Integer fileFoto;

}