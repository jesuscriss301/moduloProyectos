package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
@Getter
@Data
public class ProyectoPersonaId implements Serializable {
    private static final long serialVersionUID = -6715348849253857916L;
    @Column(name = "id_proyecto", nullable = false)
    private Integer proyecto;

    @Column(name = "id_persona", nullable = false)
    private Integer persona;

    @Column(name = "id_etapa", nullable = false)
    private Integer etapa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProyectoPersonaId entity = (ProyectoPersonaId) o;
        return Objects.equals(this.proyecto, entity.proyecto) &&
                Objects.equals(this.etapa, entity.etapa) &&
                Objects.equals(this.persona, entity.persona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proyecto, etapa, persona);
    }

}