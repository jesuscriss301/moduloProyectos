package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.EtapaProyecto;
import com.carboexco.moduloProyectos.entity.Proyecto;
import com.carboexco.moduloProyectos.entity.Tarea;
import com.carboexco.moduloProyectos.entity.Tipoproyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {
    List<Tarea> findByidEtapaProyecto(EtapaProyecto Id);

    List<Tarea> findByIdEtapaProyecto_IdProyecto_IdAndIdEtapaProyecto_IdEtapa_Id(Integer idProyecto, Integer idEtapa);

    List<Tarea> findByIdEtapaProyecto_IdProyecto_Id(Integer idProyecto);




}