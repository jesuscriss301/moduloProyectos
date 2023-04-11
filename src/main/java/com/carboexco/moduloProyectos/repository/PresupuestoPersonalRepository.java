package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.PresupuestoPersonal;
import com.carboexco.moduloProyectos.entity.PresupuestoPersonalId;
import com.carboexco.moduloProyectos.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresupuestoPersonalRepository extends JpaRepository<PresupuestoPersonal, PresupuestoPersonalId> {
    List<PresupuestoPersonal> findByIdPresupuesto_Id(Integer presupuesto);

    List<PresupuestoPersonal> findByIdPresupuesto_IdProyecto_Id(Integer id);

}