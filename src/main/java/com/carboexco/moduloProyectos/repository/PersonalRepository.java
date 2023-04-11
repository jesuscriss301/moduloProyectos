package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalRepository extends JpaRepository<Personal, Integer> {
    List<Personal> findByIdCargo(Integer idCargo);
}