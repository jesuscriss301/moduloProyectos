package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(name = "proyecto")
public class Proyecto {
    @Id
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

}