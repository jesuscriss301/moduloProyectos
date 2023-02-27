package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Data
@Table(name = "tarea_persona")
public class TareaPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "idTarea_id_tarea", referencedColumnName = "id_tarea", nullable = false),
            @JoinColumn(name = "idTarea_id_etapa_proyecto", referencedColumnName = "id_etapa_proyecto", nullable = false)
    })
    private Tarea idTarea;

    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_etapa", nullable = false)
    private Etapa idEtapa;


}