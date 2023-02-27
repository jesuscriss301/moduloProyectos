package com.carboexco.moduloProyectos.controller;
import com.carboexco.moduloProyectos.entity.Estado;
import com.carboexco.moduloProyectos.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> getEstadoAll() {
        return estadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Estado getEstadobyId(@PathVariable int id) {

        Optional<Estado> estado = estadoRepository.findById(id);
        if (estado.isPresent()) {
            return estado.get();
        }
        return null;
    }

    @PostMapping
    public Estado postEstado(@RequestBody Estado estado) {
        estadoRepository.save(estado);
        return estado;
    }

    @PutMapping("/{id}")
    public Estado putEstadobyId(@PathVariable int id, @RequestBody Estado estado) {

        Optional<Estado> estadoCurrent = estadoRepository.findById(id);

        if (estadoCurrent.isPresent()) {
            Estado estadoReturn = estadoCurrent.get();

            estadoReturn.setNombreEstado(estado.getNombreEstado());
            estadoRepository.save(estadoReturn);
            return estadoReturn;
        }

        return null;
    }


    @DeleteMapping("/{id}")
    public Estado deleteEstadobyId(@PathVariable int id) {

        Optional<Estado> estado = estadoRepository.findById(id);

        if (estado.isPresent()) {
            Estado estadoReturn = estado.get();
            estadoRepository.deleteById(id);
            return estadoReturn;
        }

        return null;
    }
}