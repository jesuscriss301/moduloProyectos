package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "proyecto_persona")
public class ProyectoPersona {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto idProyecto;

    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_etapa", nullable = false)
    private Etapa idEtapa;

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Etapa getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Etapa idEtapa) {
        this.idEtapa = idEtapa;
    }

}