package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.Presupuesto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresupuestoRepository extends JpaRepository<Presupuesto, Integer> {
    List<Presupuesto> findByIdProyecto_IdAndIdEstado_Id(Integer idProyecto, Integer idEstado);

    List<Presupuesto> findByIdProyecto_IdOrderByIdDesc(Integer id);



}