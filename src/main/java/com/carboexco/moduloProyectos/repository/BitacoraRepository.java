package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.Bitacora;
import com.carboexco.moduloProyectos.entity.Etapa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BitacoraRepository extends JpaRepository<Bitacora, Integer> {
    List<Bitacora> findByIdTarea_IdAndIdTarea_IdEtapaProyecto_IdEtapa(Integer id, Etapa idEtapa);

    List<Bitacora> findByIdTarea_IdOrderByFechaHoraAsc(Integer id);

    List<Bitacora> findByIdTarea_IdOrderByFechaHoraDesc(Integer id);




}