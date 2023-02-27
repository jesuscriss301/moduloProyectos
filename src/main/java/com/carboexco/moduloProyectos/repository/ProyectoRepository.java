package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
}