package com.carboexco.moduloProyectos.repository;

import com.carboexco.moduloProyectos.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRepository extends JpaRepository<Personal, Integer> {
}