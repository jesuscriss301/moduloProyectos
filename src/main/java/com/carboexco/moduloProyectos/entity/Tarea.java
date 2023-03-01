package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Data
@Table(name = "tarea")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarea", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_etapa_proyecto", nullable = false)
    private EtapaProyecto idEtapaProyecto;

    @Column(name = "nombre_tarea", nullable = false, length = 500)
    private String nombreTarea;

    @Lob
    @Column(name = "descripcion_tarea", nullable = false)
    private String descripcionTarea;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_final", nullable = false)
    private LocalDate fechaFinal;

    @Column(name = "fecha_inicio_real")
    private LocalDate fechaInicioReal;

    @Column(name = "fecha_final_real")
    private LocalDate fechaFinalReal;

}