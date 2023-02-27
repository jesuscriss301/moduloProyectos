package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PresupuestoMaterialId implements Serializable {
    private static final long serialVersionUID = -1577582439286405697L;
    @Column(name = "id_presupuesto", nullable = false)
    private Integer idPresupuesto;

    @Column(name = "id_material", nullable = false)
    private Integer idMaterial;

    public Integer getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Integer idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PresupuestoMaterialId entity = (PresupuestoMaterialId) o;
        return Objects.equals(this.idPresupuesto, entity.idPresupuesto) &&
                Objects.equals(this.idMaterial, entity.idMaterial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPresupuesto, idMaterial);
    }

}