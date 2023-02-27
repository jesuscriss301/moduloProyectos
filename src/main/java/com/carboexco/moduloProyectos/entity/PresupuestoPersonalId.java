package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PresupuestoPersonalId implements Serializable {
    private static final long serialVersionUID = 4777910667440516042L;
    @Column(name = "id_presupuesto", nullable = false)
    private Integer idPresupuesto;

    @Column(name = "id_personal", nullable = false)
    private Integer idPersonal;

    public Integer getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Integer idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public Integer getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(Integer idPersonal) {
        this.idPersonal = idPersonal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PresupuestoPersonalId entity = (PresupuestoPersonalId) o;
        return Objects.equals(this.idPersonal, entity.idPersonal) &&
                Objects.equals(this.idPresupuesto, entity.idPresupuesto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersonal, idPresupuesto);
    }

}