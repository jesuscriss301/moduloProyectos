package com.carboexco.moduloProyectos.controller;
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

    @GetMapping("/tarea/{id}")
    public List<TareaPersona> getTareaById(@PathVariable int id) {
        List<TareaPersona> tareaPersona = tareaPersonaRepository.findAll();
        List<TareaPersona> tareaPersonatarea = new ArrayList<TareaPersona>();
        for (TareaPersona i : tareaPersona) {
            if (i.getIdTarea().getId()==id){
                tareaPersonatarea.add(i);
            }
        }
        return tareaPersonatarea;
    }

    @GetMapping("/{id}")
    public TareaPersona getTareaPersonabyId(@PathVariable int id) {

        Optional<TareaPersona> tareaPersona = tareaPersonaRepository.findById((long) id);

        if (tareaPersona.isPresent()) {
            return tareaPersona.get();
        }

        return null;
    }

    @PostMapping
    public TareaPersona postTareaPersona(@RequestBody TareaPersona tareaPersona) {
        tareaPersonaRepository.save(tareaPersona);
        return tareaPersona;
    }

    @PutMapping("/{id}")
    public TareaPersona putTareaPersonabyId(@PathVariable int id, @RequestBody TareaPersona tareaPersona) {

        Optional<TareaPersona> tareaPersonaCurrent = tareaPersonaRepository.findById((long)id);

        if (tareaPersonaCurrent.isPresent()) {
            TareaPersona tareaPersonaReturn = tareaPersonaCurrent.get();

            tareaPersonaReturn.setFecha(tareaPersona.getFecha());
            tareaPersonaReturn.setIdPersona(tareaPersona.getIdPersona());

            tareaPersonaRepository.save(tareaPersonaReturn);
            return tareaPersonaReturn;
        }

        return null;
    }


    @DeleteMapping("/{id}")
    public TareaPersona deleteTareaPersonabyId(@PathVariable int id) {

        Optional<TareaPersona> tareaPersona = tareaPersonaRepository.findById((long)id);

        if (tareaPersona.isPresent()) {
            TareaPersona tareaPersonaReturn = tareaPersona.get();
            tareaPersonaRepository.deleteById((long)id);
            return tareaPersonaReturn;
        }

        return null;
    }
}