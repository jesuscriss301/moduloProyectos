package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@Table(name = "prioridad")
public class Prioridad {
    @Id
    @Column(name = "id_prioridad", nullable = false)
    private Integer id;

    @Column(name = "nombre_prioridad", nullable = false, length = 10)
    private String nombrePrioridad;

}