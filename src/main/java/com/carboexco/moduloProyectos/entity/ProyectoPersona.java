package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Data
@Table(name = "proyecto_persona")
public class ProyectoPersona {
    @EmbeddedId
    private ProyectoPersonaId id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

}