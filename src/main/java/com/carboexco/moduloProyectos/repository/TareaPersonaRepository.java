package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.TareaPersona;
import com.carboexco.moduloProyectos.entity.TareaPersonaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaPersonaRepository extends JpaRepository<TareaPersona, TareaPersonaId> {
}