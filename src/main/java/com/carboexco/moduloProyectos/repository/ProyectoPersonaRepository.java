package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.ProyectoPersona;
import com.carboexco.moduloProyectos.entity.ProyectoPersonaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoPersonaRepository extends JpaRepository<ProyectoPersona, ProyectoPersonaId> {
}