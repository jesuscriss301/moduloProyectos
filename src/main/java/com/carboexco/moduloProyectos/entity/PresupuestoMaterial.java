package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
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
    
}