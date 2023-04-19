package com.carboexco.moduloProyectos.controller;

import com.carboexco.moduloProyectos.entity.ProyectoPersona;
import com.carboexco.moduloProyectos.entity.Tarea;
import com.carboexco.moduloProyectos.entity.TareaPersona;
import com.carboexco.moduloProyectos.repository.TareaRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        List<Tarea> tarea= tareaRepository.findByIdEtapaProyecto_IdProyecto_Id(id);

        return tarea;
    }

    public static List<Tarea> gettareasTerminadas(List<Tarea> tareas){
        List<Tarea> tareasListas = new ArrayList<>();
        for (Tarea i : tareas){
            if(i.getFechaFinalReal()!=null && i.getFechaInicioReal()!=null/*&& i.getIdEtapaProyecto().getIdEstado().getId()==2*/){

                tareasListas.add(i);
            }
        }
        return tareasListas;
    }

    public static List<Tarea> getTareasEnEjecucion(List<Tarea> tareas){
        List<Tarea> tareasListas = new ArrayList<>();
        for (Tarea i : tareas){
            if(i.getFechaInicioReal()!=null && i.getFechaFinalReal()==null){
                tareasListas.add(i);
            }
        }
        return tareasListas;
    }

    public static List<Tarea> getTareasEnEspera(List<Tarea> tareas){
        List<Tarea> tareasListas = new ArrayList<>();
        for (Tarea i : tareas){
            if(i.getFechaInicioReal()==null && i.getFechaFinalReal()==null){
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

    @GetMapping("/{proyecto}/{etapa}")
    public List<Tarea> tareasEtapaProyecto(@PathVariable int proyecto,@PathVariable int etapa){

        return tareaRepository.findByIdEtapaProyecto_IdProyecto_IdAndIdEtapaProyecto_IdEtapa_Id(proyecto,etapa);
    }

    public String personaTarea(@NotNull TareaPersona tareaP) {
        return tareaP.getId().getIdTarea().toString();
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