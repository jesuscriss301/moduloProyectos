package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.Proyecto;
import com.carboexco.moduloProyectos.entity.Tipoproyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

    List<Proyecto> findByidTipoProyecto(Tipoproyecto tipoProyectoId);
}