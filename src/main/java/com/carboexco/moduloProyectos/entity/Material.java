package com.carboexco.moduloProyectos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@Table(name = "material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material", nullable = false)
    private Integer id;

    @Column(name = "tipo_material", nullable = false, length = 15)
    private String tipoMaterial;

    @Column(name = "id_producto", length = 200)
    private String idProducto;

}