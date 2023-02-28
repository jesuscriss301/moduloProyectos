package com.carboexco.moduloProyectos.controller;
import com.carboexco.moduloProyectos.entity.ProyectoPersona;
import com.carboexco.moduloProyectos.repository.ProyectoPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/proyectoPersonas")
public class ProyectoPersonaController {

    @Autowired
    ProyectoPersonaRepository proyectoPersonaRepository;

    @GetMapping
    public List<ProyectoPersona> getProyectoPersonaAll() {
        return proyectoPersonaRepository.findAll();
    }

    @GetMapping("/proyecto/{id}")
    public List<ProyectoPersona> getTareaById(@PathVariable int id) {
        List<ProyectoPersona> proyectoPersona = proyectoPersonaRepository.findAll();
        List<ProyectoPersona> proyectoPersonatarea = new ArrayList<ProyectoPersona>();
        for (ProyectoPersona i : proyectoPersona) {
            if (i.getIdProyecto().getId()==id){
                proyectoPersonatarea.add(i);
            }
        }
        return proyectoPersonatarea;
    }

    @GetMapping("/{id}")
    public ProyectoPersona getProyectoPersonabyId(@PathVariable int id) {

        Optional<ProyectoPersona> proyectoPersona = proyectoPersonaRepository.findById(id);

        if (proyectoPersona.isPresent()) {
            return proyectoPersona.get();
        }

        return null;
    }

    @PostMapping
    public ProyectoPersona postProyectoPersona(@RequestBody ProyectoPersona proyectoPersona) {
        proyectoPersonaRepository.save(proyectoPersona);
        return proyectoPersona;
    }

    @PutMapping("/{id}")
    public ProyectoPersona putProyectoPersonabyId(@PathVariable int id, @RequestBody ProyectoPersona proyectoPersona) {

        Optional<ProyectoPersona> proyectoPersonaCurrent = proyectoPersonaRepository.findById(id);

        if (proyectoPersonaCurrent.isPresent()) {
            ProyectoPersona proyectoPersonaReturn = proyectoPersonaCurrent.get();

            proyectoPersonaReturn.setFecha(proyectoPersona.getFecha());
            proyectoPersonaReturn.setIdPersona(proyectoPersona.getIdPersona());

            proyectoPersonaRepository.save(proyectoPersonaReturn);
            return proyectoPersonaReturn;
        }

        return null;
    }


    @DeleteMapping("/{id}")
    public ProyectoPersona deleteProyectoPersonabyId(@PathVariable int id) {

        Optional<ProyectoPersona> proyectoPersona = proyectoPersonaRepository.findById(id);

        if (proyectoPersona.isPresent()) {
            ProyectoPersona proyectoPersonaReturn = proyectoPersona.get();
            proyectoPersonaRepository.deleteById(id);
            return proyectoPersonaReturn;
        }

        return null;
    }
}