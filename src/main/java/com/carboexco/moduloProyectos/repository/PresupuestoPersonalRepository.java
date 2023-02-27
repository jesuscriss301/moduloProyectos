package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.PresupuestoPersonal;
import com.carboexco.moduloProyectos.entity.PresupuestoPersonalId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresupuestoPersonalRepository extends JpaRepository<PresupuestoPersonal, PresupuestoPersonalId> {
}