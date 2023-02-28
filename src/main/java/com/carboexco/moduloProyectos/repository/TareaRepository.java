package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {
}