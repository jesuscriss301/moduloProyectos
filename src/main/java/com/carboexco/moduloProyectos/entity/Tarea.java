package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tarea")
public class Tarea {
    @EmbeddedId
    private TareaId id;

    @MapsId("idEtapaProyecto")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
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

    public TareaId getId() {
        return id;
    }

    public void setId(TareaId id) {
        this.id = id;
    }

    public EtapaProyecto getIdEtapaProyecto() {
        return idEtapaProyecto;
    }

    public void setIdEtapaProyecto(EtapaProyecto idEtapaProyecto) {
        this.idEtapaProyecto = idEtapaProyecto;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public LocalDate getFechaInicioReal() {
        return fechaInicioReal;
    }

    public void setFechaInicioReal(LocalDate fechaInicioReal) {
        this.fechaInicioReal = fechaInicioReal;
    }

    public LocalDate getFechaFinalReal() {
        return fechaFinalReal;
    }

    public void setFechaFinalReal(LocalDate fechaFinalReal) {
        this.fechaFinalReal = fechaFinalReal;
    }

}