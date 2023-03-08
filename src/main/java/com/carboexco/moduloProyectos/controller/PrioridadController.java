package com.carboexco.moduloProyectos.controller;

import com.carboexco.moduloProyectos.entity.Prioridad;
import com.carboexco.moduloProyectos.repository.PrioridadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/prioridads")
public class PrioridadController {

    @Autowired
    PrioridadRepository prioridadRepository;

    @GetMapping
    public List<Prioridad> getPrioridadAll() {
        return prioridadRepository.findAll();
    }

    @GetMapping("/{id}")
    public Prioridad getPrioridadbyId(@PathVariable int id) {

        Optional<Prioridad> prioridad = prioridadRepository.findById(id);
        if (prioridad.isPresent()) {
            return prioridad.get();
        }
        return null;
    }

    @PostMapping
    public Prioridad postPrioridad(@RequestBody Prioridad prioridad) {
        prioridadRepository.save(prioridad);
        return prioridad;
    }

    @PutMapping("/{id}")
    public Prioridad putPrioridadbyId(@PathVariable int id, @RequestBody Prioridad prioridad) {

        Optional<Prioridad> prioridadCurrent = prioridadRepository.findById(id);

        if (prioridadCurrent.isPresent()) {
            Prioridad prioridadReturn = prioridadCurrent.get();

            prioridadReturn.setNombrePrioridad(prioridad.getNombrePrioridad());
            prioridadRepository.save(prioridadReturn);
            return prioridadReturn;
        }

        return null;
    }


    @DeleteMapping("/{id}")
    public Prioridad deletePrioridadbyId(@PathVariable int id) {

        Optional<Prioridad> prioridad = prioridadRepository.findById(id);

        if (prioridad.isPresent()) {
            Prioridad prioridadReturn = prioridad.get();
            prioridadRepository.deleteById(id);
            return prioridadReturn;
        }

        return null;
    }
}