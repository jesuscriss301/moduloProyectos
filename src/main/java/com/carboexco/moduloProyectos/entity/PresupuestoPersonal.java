package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@Table(name = "presupuesto_personal")
public class PresupuestoPersonal {
    @EmbeddedId
    private PresupuestoPersonalId id;

    @MapsId("idPresupuesto")
    @ManyToOne
    @JoinColumn(name = "id_presupuesto", nullable = false)
    private Presupuesto idPresupuesto;

    @MapsId("idPersonal")
    @ManyToOne
    @JoinColumn(name = "id_personal", nullable = false)
    private Personal idPersonal;

    @Column(name = "costo", columnDefinition = "INT UNSIGNED")
    private Long costo;

    @Column(name = "cantidad", columnDefinition = "INT UNSIGNED not null")
    private Long cantidad;

    @Column(name = "tiempo_uso", columnDefinition = "INT UNSIGNED")
    private Long tiempoUso;

}