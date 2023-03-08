package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.ProyectoPersona;
import com.carboexco.moduloProyectos.entity.ProyectoPersonaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProyectoPersonaRepository extends JpaRepository<ProyectoPersona, ProyectoPersonaId> {

    List<ProyectoPersona> findById_ProyectoAndId_Etapa(Integer proyecto, Integer etapa);

    ProyectoPersona findById_Proyecto(Integer proyecto);

}