package com.carboexco.moduloProyectos.controller;

import com.carboexco.moduloProyectos.entity.Proyecto;
import com.carboexco.moduloProyectos.entity.Tarea;
import com.carboexco.moduloProyectos.entity.TareaPersona;
import com.carboexco.moduloProyectos.repository.TareaPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/tareaPersonas")
public class TareaPersonaController {

    @Autowired
    TareaPersonaRepository tareaPersonaRepository;

    @GetMapping
    public List<TareaPersona> getTareaPersonaAll() {
        return tareaPersonaRepository.findAll();
    }

    @GetMapping("/tareas/{id}")
    public List<TareaPersona> getProyectoById(@PathVariable int id) {
        List<TareaPersona> tareaPersona = tareaPersonaRepository.findById_IdTarea( id);
        return tareaPersona;
    }

    @GetMapping("/persona/{id}")
    public List<TareaPersona> getPresupuestoById(@PathVariable int id) {
        List<TareaPersona> tareaPersona = tareaPersonaRepository.findAll();
        List<TareaPersona> tareaPersonatarea = new ArrayList<TareaPersona>();
        for (TareaPersona i : tareaPersona) {
            if (i.getId().getIdPersona()==id){
                tareaPersonatarea.add(i);
            }
        }
        return tareaPersonatarea;
    }


    @PostMapping
    public TareaPersona postTareaPersona(@RequestBody TareaPersona tareaPersona) {
        tareaPersonaRepository.save(tareaPersona);
        return tareaPersona;
    }
    /*
    @PutMapping("/{idPr}/{idE}")
    public TareaPersona putTareaPersonabyId(@PathVariable int idPr, @PathVariable int idE,@RequestBody TareaPersona tareaPersona) {

        Optional<List<TareaPersona>> tareaPersonaCurrent = Optional.of(tareaPersonaRepository.findAll());

        if (tareaPersonaCurrent.isPresent()) {
            List<TareaPersona> tareaPersonaReturn = tareaPersonaCurrent.get();
            TareaPersona tareaPersonaIdReturn = new TareaPersona();

            for (TareaPersona i : tareaPersonaReturn) {
                if (i.getId().getIdTarea() == idPr && i.getId().getIdPersona() == idPr && i.getId().getIdEtapa() == idE) {

                    tareaPersonaIdReturn = i;
                    tareaPersonaIdReturn.setFecha(tareaPersona.getFecha());
                    return tareaPersonaIdReturn;
                }
            }

        }
        return null;
    }

    @DeleteMapping("/{idPr}/{idE}")
    public TareaPersona deleteTareaPersonabyId(@PathVariable int idPr, @PathVariable int idE) {
        Optional<List<TareaPersona>> tareaPersonaCurrent = Optional.of(tareaPersonaRepository.findAll());

        if (tareaPersonaCurrent.isPresent()) {
            List<TareaPersona> tareaPersonaReturn = tareaPersonaCurrent.get();
            TareaPersona tareaPersonaIdReturn = new TareaPersona();

            for (TareaPersona i : tareaPersonaReturn) {
                if (i.getId().getIdTarea() == idPr && i.getId().getIdPersona() == idPr && i.getId().getIdEtapa() == idE) {
                    tareaPersonaRepository.delete(i);
                    return i;
                }
            }
        }
        return null;
    }

    @GetMapping("/tareaEtapa/{idtarea}/{idetapa}")
    public List<TareaPersona> tareaPersona(@PathVariable int idtarea, @PathVariable int idetapa){

        List<TareaPersona> tareaPersona = tareaPersonaRepository.findById_IdTareaAndId_IdEtapa(idtarea,idetapa);
         return tareaPersona;
    }
*/
}
