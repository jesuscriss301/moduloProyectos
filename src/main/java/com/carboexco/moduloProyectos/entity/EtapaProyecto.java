package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Data
@Table(name = "etapa_proyecto")
public class EtapaProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto idProyecto;

    @ManyToOne
    @JoinColumn(name = "id_etapa", nullable = false)
    private Etapa idEtapa;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_final")
    private LocalDate fechaFinal;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado idEstado;

}