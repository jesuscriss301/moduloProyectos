package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.ProyectoPersona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoPersonaRepository<, ID> extends JpaRepository<ProyectoPersona, ID> {
}