package com.carboexco.moduloProyectos.controller;

import com.carboexco.moduloProyectos.entity.Tipoproyecto;
import com.carboexco.moduloProyectos.repository.TipoproyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/tipoproyectos")

public class TipoproyectoController {

    @Autowired
    TipoproyectoRepository tipoproyectoRepository;

    @GetMapping
    public List<Tipoproyecto> getTipoproyectoAll() {
        return tipoproyectoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tipoproyecto getTipoproyectobyId(@PathVariable int id) {

        Optional<Tipoproyecto> tipoproyecto = tipoproyectoRepository.findById(id);
        if (tipoproyecto.isPresent()) {
            return tipoproyecto.get();
        }
        return null;
    }

    @PostMapping
    public Tipoproyecto postTipoproyecto(@RequestBody Tipoproyecto tipoproyecto) {
        tipoproyectoRepository.save(tipoproyecto);
        return tipoproyecto;
    }

    @PutMapping("/{id}")
    public Tipoproyecto putTipoproyectobyId(@PathVariable int id, @RequestBody Tipoproyecto tipoproyecto) {

        Optional<Tipoproyecto> tipoproyectoCurrent = tipoproyectoRepository.findById(id);

        if (tipoproyectoCurrent.isPresent()) {
            Tipoproyecto tipoproyectoReturn = tipoproyectoCurrent.get();

            tipoproyectoReturn.setNombre(tipoproyecto.getNombre());
            tipoproyectoRepository.save(tipoproyectoReturn);
            return tipoproyectoReturn;
        }

        return null;
    }


    @DeleteMapping("/{id}")
    public Tipoproyecto deleteTipoproyectobyId(@PathVariable int id) {

        Optional<Tipoproyecto> tipoproyecto = tipoproyectoRepository.findById(id);

        if (tipoproyecto.isPresent()) {
            Tipoproyecto tipoproyectoReturn = tipoproyecto.get();
            tipoproyectoRepository.deleteById(id);
            return tipoproyectoReturn;
        }

        return null;
    }
}