package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(name = "presupuesto_personal")
public class PresupuestoPersonal {
    @EmbeddedId
    private PresupuestoPersonalId id;

    @MapsId("idPresupuesto")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_presupuesto", nullable = false)
    private Presupuesto idPresupuesto;

    @MapsId("idPersonal")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_personal", nullable = false)
    private Personal idPersonal;

    @Column(name = "costo", columnDefinition = "INT UNSIGNED")
    private Long costo;

    @Column(name = "cantidad", columnDefinition = "INT UNSIGNED not null")
    private Long cantidad;

    @Column(name = "tiempo_uso", columnDefinition = "INT UNSIGNED")
    private Long tiempoUso;

    public PresupuestoPersonalId getId() {
        return id;
    }

    public void setId(PresupuestoPersonalId id) {
        this.id = id;
    }

    public Presupuesto getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Presupuesto idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public Personal getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(Personal idPersonal) {
        this.idPersonal = idPersonal;
    }

    public Long getCosto() {
        return costo;
    }

    public void setCosto(Long costo) {
        this.costo = costo;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Long getTiempoUso() {
        return tiempoUso;
    }

    public void setTiempoUso(Long tiempoUso) {
        this.tiempoUso = tiempoUso;
    }

}