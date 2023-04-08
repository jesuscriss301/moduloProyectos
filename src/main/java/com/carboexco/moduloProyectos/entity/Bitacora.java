package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Entity
@Setter
@Getter
@Data
@Table(name = "bitacora")
public class Bitacora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bitacora", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_tarea", nullable = false)
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