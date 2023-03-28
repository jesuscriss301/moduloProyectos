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
public class TareaPersonaId implements Serializable {
    private static final long serialVersionUID = -1812750535738382379L;
    @Column(name = "id_tarea", nullable = false)
    private Integer idTarea;

    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TareaPersonaId entity = (TareaPersonaId) o;
        return Objects.equals(this.idTarea, entity.idTarea) &&
                Objects.equals(this.idPersona, entity.idPersona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTarea, idPersona);
    }

}