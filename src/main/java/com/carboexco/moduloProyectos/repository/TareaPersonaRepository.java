package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.TareaPersona;
import com.carboexco.moduloProyectos.entity.TareaPersonaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaPersonaRepository extends JpaRepository<TareaPersona, TareaPersonaId> {

    List<TareaPersona> findById_IdTarea(Integer idTarea);

}