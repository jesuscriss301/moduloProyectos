package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    List<Material> findByIdProductoAndTipoMaterial(Integer idProducto, String tipoMaterial);


}