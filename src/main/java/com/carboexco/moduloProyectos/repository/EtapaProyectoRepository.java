package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.Etapa;
import com.carboexco.moduloProyectos.entity.EtapaProyecto;
import com.carboexco.moduloProyectos.entity.Proyecto;
import com.carboexco.moduloProyectos.entity.Tipoproyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EtapaProyectoRepository extends JpaRepository<EtapaProyecto, Integer> {
    Optional<EtapaProyecto> findByidProyectoAndIdEtapa(Proyecto proyecto, Etapa etapa);


    Optional<EtapaProyecto> findByIdProyecto_IdAndIdEtapa_Id(Integer id, Integer id1);
    
    

    List<EtapaProyecto> findByIdProyecto_IdAndIdEstado_Id(Integer proyecto, Integer estado);

}