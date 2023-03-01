package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "bitacora")
public class Bitacora {
    @Id
    @Column(name = "id_bitacora", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tarea getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Tarea idTarea) {
        this.idTarea = idTarea;
    }

    public String getDescripcionBitacora() {
        return descripcionBitacora;
    }

    public void setDescripcionBitacora(String descripcionBitacora) {
        this.descripcionBitacora = descripcionBitacora;
    }

    public String getObservacionBitacora() {
        return observacionBitacora;
    }

    public void setObservacionBitacora(String observacionBitacora) {
        this.observacionBitacora = observacionBitacora;
    }

    public Instant getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Instant fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getFileFoto() {
        return fileFoto;
    }

    public void setFileFoto(Integer fileFoto) {
        this.fileFoto = fileFoto;
    }

}