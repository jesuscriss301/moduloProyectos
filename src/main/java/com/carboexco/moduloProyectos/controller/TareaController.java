package com.carboexco.moduloProyectos.controller;
import com.carboexco.moduloProyectos.entity.Etapa;
import com.carboexco.moduloProyectos.entity.EtapaProyecto;
import com.carboexco.moduloProyectos.entity.Tarea;
import com.carboexco.moduloProyectos.repository.EtapaProyectoRepository;
import com.carboexco.moduloProyectos.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    TareaRepository tareaRepository;


    @GetMapping
    public List<Tarea> getTareaAll() {
        return tareaRepository.findAll();
    }

    @GetMapping("proyecto/{id}")
    public List<Tarea> getProyectoById(@PathVariable int id) {
        List<Tarea> tarea= tareaRepository.findAll();

        List<Tarea> tareaetapa = new ArrayList<Tarea>();
            for (Tarea i : tarea) {
                if (i.getIdEtapaProyecto().getId()==id){
                    tareaetapa.add(i);
                }
            }
        return tareaetapa;
    }

    public static List<Tarea> gettareasTerminadas(List<Tarea> tareas){
        List<Tarea> tareasListas = new ArrayList<>();
        for (Tarea i : tareas){
            if(i.getFechaFinalReal()!=null && i.getIdEtapaProyecto().getIdEstado().getId()==2){
                tareasListas.add(i);
            }
        }
        return tareasListas;
    }



    @GetMapping("/{id}")
    public Tarea getTareabyId(@PathVariable int id) {

        Optional<Tarea> tarea = tareaRepository.findById(id);

        if (tarea.isPresent()) {
            return tarea.get();
        }

        return null;
    }

    @PostMapping
    public Tarea postTarea(@RequestBody Tarea tarea) {
        tareaRepository.save(tarea);
        return tarea;
    }

    @PutMapping("/{id}")
    public Tarea putTareabyId(@PathVariable int id, @RequestBody Tarea tarea) {

        Optional<Tarea> tareaCurrent = tareaRepository.findById(id);

        if (tareaCurrent.isPresent()) {
            Tarea tareaReturn = tareaCurrent.get();

            tareaReturn.setDescripcionTarea(tarea.getDescripcionTarea());
            tareaReturn.setNombreTarea(tarea.getNombreTarea());
            tareaReturn.setFechaInicio(tarea.getFechaInicio());
            tareaReturn.setFechaFinal(tarea.getFechaFinal());
            tareaReturn.setFechaInicioReal(tarea.getFechaInicioReal());
            tareaReturn.setFechaFinalReal(tarea.getFechaFinalReal());

            tareaRepository.save(tareaReturn);
            return tareaReturn;
        }

        return null;
    }


    @DeleteMapping("/{id}")
    public Tarea deleteTareabyId(@PathVariable int id) {

        Optional<Tarea> tarea = tareaRepository.findById(id);

        if (tarea.isPresent()) {
            Tarea tareaReturn = tarea.get();
            tareaRepository.deleteById(id);
            return tareaReturn;
        }

        return null;
    }
}