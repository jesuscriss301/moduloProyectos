package com.carboexco.moduloProyectos.controller;
import com.carboexco.moduloProyectos.entity.ProyectoPersona;
import com.carboexco.moduloProyectos.entity.ProyectoPersonaId;
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
    public List<ProyectoPersona> getProyectoById(@PathVariable int id) {
        List<ProyectoPersona> proyectoPersona = proyectoPersonaRepository.findAll();
        List<ProyectoPersona> proyectoPersonatarea = new ArrayList<ProyectoPersona>();
        for (ProyectoPersona i : proyectoPersona) {
            if (i.getId().getIdProyecto()==id){
                proyectoPersonatarea.add(i);
            }
        }
        return proyectoPersonatarea;
    }

    @GetMapping("/persona/{id}")
    public List<ProyectoPersona> getPresupuestoById(@PathVariable int id) {
        List<ProyectoPersona> proyectoPersona = proyectoPersonaRepository.findAll();
        List<ProyectoPersona> proyectoPersonatarea = new ArrayList<ProyectoPersona>();
        for (ProyectoPersona i : proyectoPersona) {
            if (i.getId().getIdPersona()==id){
                proyectoPersonatarea.add(i);
            }
        }
        return proyectoPersonatarea;
    }


    @PostMapping
    public ProyectoPersona postProyectoPersona(@RequestBody ProyectoPersona proyectoPersona) {
        proyectoPersonaRepository.save(proyectoPersona);
        return proyectoPersona;
    }

    @PutMapping("/{idPr}/{idP}/{idE}")
    public ProyectoPersona putProyectoPersonabyId(@PathVariable int idPr,@PathVariable int idP, @PathVariable int idE,@RequestBody ProyectoPersona proyectoPersona) {

        Optional<List<ProyectoPersona>> proyectoPersonaCurrent = Optional.of(proyectoPersonaRepository.findAll());

        if (proyectoPersonaCurrent.isPresent()) {
            List<ProyectoPersona> proyectoPersonaReturn = proyectoPersonaCurrent.get();
            ProyectoPersona proyectoPersonaIdReturn = new ProyectoPersona();

            for (ProyectoPersona i : proyectoPersonaReturn) {
                if (i.getId().getIdProyecto() == idPr && i.getId().getIdPersona() == idPr && i.getId().getIdEtapa() == idE) {

                    proyectoPersonaIdReturn = i;
                    proyectoPersonaIdReturn.setFecha(proyectoPersona.getFecha());
                    return proyectoPersonaIdReturn;
                }
            }

        }
        return null;
    }

    @DeleteMapping("/{idPr}/{idP}/{idE}")
    public ProyectoPersona deleteProyectoPersonabyId(@PathVariable int idPr,@PathVariable int idP, @PathVariable int idE) {
        Optional<List<ProyectoPersona>> proyectoPersonaCurrent = Optional.of(proyectoPersonaRepository.findAll());

        if (proyectoPersonaCurrent.isPresent()) {
            List<ProyectoPersona> proyectoPersonaReturn = proyectoPersonaCurrent.get();
            ProyectoPersona proyectoPersonaIdReturn = new ProyectoPersona();

            for (ProyectoPersona i : proyectoPersonaReturn) {
                if (i.getId().getIdProyecto() == idPr && i.getId().getIdPersona() == idPr && i.getId().getIdEtapa() == idE) {
                    proyectoPersonaRepository.delete(i);
                    return i;
                }
            }

        }
        return null;
    }
}