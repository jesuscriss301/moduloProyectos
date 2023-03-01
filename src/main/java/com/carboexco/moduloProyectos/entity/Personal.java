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
@Table(name = "personal")
public class Personal {
    @Id
    @Column(name = "id_personal", nullable = false)
    private Integer id;

    @Column(name = "id_cargo", nullable = false)
    private Integer idCargo;

}