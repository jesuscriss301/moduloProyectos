package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "presupuesto_material")
public class PresupuestoMaterial {
    @EmbeddedId
    private PresupuestoMaterialId id;

    @MapsId("idPresupuesto")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_presupuesto", nullable = false)
    private Presupuesto idPresupuesto;

    @MapsId("idMaterial")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_material", nullable = false)
    private Material idMaterial;

    @Column(name = "cantidad", columnDefinition = "INT UNSIGNED not null")
    private Long cantidad;

    @Column(name = "costo", columnDefinition = "INT UNSIGNED")
    private Long costo;

    @Column(name = "tiempo_uso", columnDefinition = "INT UNSIGNED")
    private Long tiempoUso;

    public PresupuestoMaterialId getId() {
        return id;
    }

    public void setId(PresupuestoMaterialId id) {
        this.id = id;
    }

    public Presupuesto getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Presupuesto idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public Material getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Material idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Long getCosto() {
        return costo;
    }

    public void setCosto(Long costo) {
        this.costo = costo;
    }

    public Long getTiempoUso() {
        return tiempoUso;
    }

    public void setTiempoUso(Long tiempoUso) {
        this.tiempoUso = tiempoUso;
    }

}