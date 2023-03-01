package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@Table(name = "proyecto")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Proyecto", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_tipo_proyecto", nullable = false)
    private Tipoproyecto idTipoProyecto;

    @Column(name = "nombre_proyecto", nullable = false, length = 500)
    private String nombreProyecto;

    @Lob
    @Column(name = "descripcion_proyecto", nullable = false)
    private String descripcionProyecto;

    @OneToOne
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