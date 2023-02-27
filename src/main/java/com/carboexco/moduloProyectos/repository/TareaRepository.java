package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.Tarea;
import com.carboexco.moduloProyectos.entity.TareaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, TareaId> {
}