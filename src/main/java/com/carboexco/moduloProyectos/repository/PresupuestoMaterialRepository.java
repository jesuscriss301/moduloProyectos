package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.PresupuestoMaterial;
import com.carboexco.moduloProyectos.entity.PresupuestoMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresupuestoMaterialRepository extends JpaRepository<PresupuestoMaterial, PresupuestoMaterialId> {
}