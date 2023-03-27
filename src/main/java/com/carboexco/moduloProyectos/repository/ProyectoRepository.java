package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.Proyecto;
import com.carboexco.moduloProyectos.entity.Tipoproyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

    List<Proyecto> findByidTipoProyecto(Tipoproyecto tipoProyectoId);

    @Query("select p from Proyecto p where p.nombreProyecto like %:nombre%")
    List<Proyecto> findByNombreProyectoLike(@Param("nombre")String nombre);


}