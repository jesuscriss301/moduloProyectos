package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
}