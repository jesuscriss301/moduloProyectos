package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.PresupuestoMaterial;
import com.carboexco.moduloProyectos.entity.PresupuestoMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresupuestoMaterialRepository extends JpaRepository<PresupuestoMaterial, PresupuestoMaterialId> {
    List<PresupuestoMaterial> findByIdPresupuesto_Id(Integer id);

    List<PresupuestoMaterial> findByIdPresupuesto_IdProyecto_Id(Integer id);

    List<PresupuestoMaterial> findByIdPresupuesto_IdAndIdMaterial_TipoMaterial(Integer id, String tipoMaterial);

}