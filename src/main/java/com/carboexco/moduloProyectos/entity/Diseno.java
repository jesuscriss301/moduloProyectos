package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "diseno")
public class Diseno {
    @Id
    @Column(name = "id_diseno", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto idProyecto;

    @Column(name = "nombre_diseno", nullable = false, length = 500)
    private String nombreDiseno;

    @Column(name = "area_terreno", nullable = false)
    private Integer areaTerreno;

    @Column(name = "id_Foto", nullable = false)
    private Integer idFoto;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado idEstado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreDiseno() {
        return nombreDiseno;
    }

    public void setNombreDiseno(String nombreDiseno) {
        this.nombreDiseno = nombreDiseno;
    }

    public Integer getAreaTerreno() {
        return areaTerreno;
    }

    public void setAreaTerreno(Integer areaTerreno) {
        this.areaTerreno = areaTerreno;
    }

    public Integer getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Integer idFoto) {
        this.idFoto = idFoto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

}