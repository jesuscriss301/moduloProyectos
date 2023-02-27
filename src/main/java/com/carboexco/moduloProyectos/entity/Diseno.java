package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Data
@Table(name = "diseno")
public class Diseno {
    @Id
    @Column(name = "id_diseno", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto idProyecto;

    @Column(name = "nombre_diseno", nullable = false, length = 500)
    private String nombreDiseno;

    @Column(name = "area_terreno", nullable = false)
    private Integer areaTerreno;

    @Column(name = "id_Foto", nullable = false)
    private Integer idFoto;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado idEstado;

}