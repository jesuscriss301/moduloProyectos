package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.Diseno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisenoRepository extends JpaRepository<Diseno, Integer> {
    List<Diseno> findByIdProyecto_IdAndIdEstado_Id(Integer idProyecto, Integer idEstado);


}