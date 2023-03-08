package com.carboexco.moduloProyectos.controller;

import com.carboexco.moduloProyectos.entity.EtapaProyecto;
import com.carboexco.moduloProyectos.repository.EtapaProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/etapa_proyectos")
public class EtapaProyectoController {

    @Autowired
    EtapaProyectoRepository etapa_proyectoRepository;

    @GetMapping
    public List<EtapaProyecto> getEtapaProyectoAll() {
        return etapa_proyectoRepository.findAll();
    }

    @GetMapping("/{id}")
    public EtapaProyecto getEtapaProyectobyId(@PathVariable int id) {

        Optional<EtapaProyecto> etapa_proyecto = etapa_proyectoRepository.findById(id);

        if (etapa_proyecto.isPresent()) {
            return etapa_proyecto.get();
        }

        return null;
    }

    @PostMapping
    public EtapaProyecto postEtapaProyecto(@RequestBody EtapaProyecto etapa_proyecto) {
        etapa_proyectoRepository.save(etapa_proyecto);
        return etapa_proyecto;
    }

    @PutMapping("/{id}")
    public EtapaProyecto putEtapaProyectobyId(@PathVariable int id, @RequestBody EtapaProyecto etapa_proyecto) {

        Optional<EtapaProyecto> etapa_proyectoCurrent = etapa_proyectoRepository.findById(id);

        if (etapa_proyectoCurrent.isPresent()) {
            EtapaProyecto etapa_proyectoReturn = etapa_proyectoCurrent.get();

            etapa_proyectoReturn.setFechaFinal(etapa_proyecto.getFechaFinal());
            etapa_proyectoReturn.setFechaInicio(etapa_proyecto.getFechaInicio());
            etapa_proyectoReturn.setIdEstado(etapa_proyecto.getIdEstado());

            etapa_proyectoRepository.save(etapa_proyectoReturn);
            return etapa_proyectoReturn;
        }

        return null;
    }


    @DeleteMapping("/{id}")
    public EtapaProyecto deleteEtapaProyectobyId(@PathVariable int id) {

        Optional<EtapaProyecto> etapa_proyecto = etapa_proyectoRepository.findById(id);

        if (etapa_proyecto.isPresent()) {
            EtapaProyecto etapa_proyectoReturn = etapa_proyecto.get();
            etapa_proyectoRepository.deleteById(id);
            return etapa_proyectoReturn;
        }

        return null;
    }
}