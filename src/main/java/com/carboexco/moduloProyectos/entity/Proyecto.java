package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "proyecto")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Proyecto", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipoProyecto", nullable = false)
    private Tipoproyecto idTipoproyecto;

    @Column(name = "nombre_proyecto", nullable = false, length = 500)
    private String nombreProyecto;

    @Lob
    @Column(name = "descripcion_proyecto", nullable = false)
    private String descripcionProyecto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_prioridad", nullable = false)
    private Prioridad idPrioridad;

    @Lob
    @Column(name = "justificacion")
    private String justificacion;

    @Lob
    @Column(name = "objetivo_general", nullable = false)
    private String objetivoGeneral;

    @Lob
    @Column(name = "objetivo_especifico")
    private String objetivoEspecifico;

    @Column(name = "ubicacion", nullable = false, length = 500)
    private String ubicacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tipoproyecto getIdTipoproyecto() {
        return idTipoproyecto;
    }

    public void setIdTipoproyecto(Tipoproyecto idTipoproyecto) {
        this.idTipoproyecto = idTipoproyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcionProyecto() {
        return descripcionProyecto;
    }

    public void setDescripcionProyecto(String descripcionProyecto) {
        this.descripcionProyecto = descripcionProyecto;
    }

    public Prioridad getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(Prioridad idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getObjetivoGeneral() {
        return objetivoGeneral;
    }

    public void setObjetivoGeneral(String objetivoGeneral) {
        this.objetivoGeneral = objetivoGeneral;
    }

    public String getObjetivoEspecifico() {
        return objetivoEspecifico;
    }

    public void setObjetivoEspecifico(String objetivoEspecifico) {
        this.objetivoEspecifico = objetivoEspecifico;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

}