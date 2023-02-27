package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.Presupuesto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresupuestoRepository extends JpaRepository<Presupuesto, Integer> {
}