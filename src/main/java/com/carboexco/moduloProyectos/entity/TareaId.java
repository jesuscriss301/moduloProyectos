package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TareaId implements Serializable {
    private static final long serialVersionUID = 3468631279761995239L;
    @Column(name = "id_tarea", nullable = false)
    private Integer idTarea;

    @Column(name = "id_etapa_proyecto", nullable = false)
    private Integer idEtapaProyecto;

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Integer getIdEtapaProyecto() {
        return idEtapaProyecto;
    }

    public void setIdEtapaProyecto(Integer idEtapaProyecto) {
        this.idEtapaProyecto = idEtapaProyecto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TareaId entity = (TareaId) o;
        return Objects.equals(this.idEtapaProyecto, entity.idEtapaProyecto) &&
                Objects.equals(this.idTarea, entity.idTarea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEtapaProyecto, idTarea);
    }

}