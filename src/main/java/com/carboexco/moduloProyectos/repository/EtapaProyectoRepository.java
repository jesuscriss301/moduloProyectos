package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.Etapa;
import com.carboexco.moduloProyectos.entity.EtapaProyecto;
import com.carboexco.moduloProyectos.entity.Proyecto;
import com.carboexco.moduloProyectos.entity.Tipoproyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtapaProyectoRepository extends JpaRepository<EtapaProyecto, Integer> {
    EtapaProyecto findByidProyectoAndIdEtapa(Proyecto proyecto, Etapa etapa);
}