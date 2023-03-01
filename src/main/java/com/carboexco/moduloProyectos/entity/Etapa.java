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
@Table(name = "etapa")
public class Etapa {
    @Id
    @Column(name = "id_etapa", nullable = false)
    private Integer id;

    @Column(name = "nombre_etapa", nullable = false, length = 30)
    private String nombreEtapa;

}