package com.carboexco.moduloProyectos.controller;
import com.carboexco.moduloProyectos.entity.PresupuestoPersonal;
import com.carboexco.moduloProyectos.entity.PresupuestoPersonalId;
import com.carboexco.moduloProyectos.repository.PresupuestoPersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/presupuestoPersonals")
public class PresupuestoPersonalController {

    @Autowired
    PresupuestoPersonalRepository presupuestoPersonalRepository;

    @GetMapping
    public List<PresupuestoPersonal> getPresupuestoPersonalAll() {
        return presupuestoPersonalRepository.findAll();
    }

    @GetMapping("/proyecto/{id}")
    public List<PresupuestoPersonal> getProyectoById(@PathVariable int id) {
        List<PresupuestoPersonal> presupuestoPersonal = presupuestoPersonalRepository.findAll();
        List<PresupuestoPersonal> presupuestoPersonaltarea = new ArrayList<PresupuestoPersonal>();
        for (PresupuestoPersonal i : presupuestoPersonal) {
            if (i.getIdPresupuesto().getIdProyecto().getId()==id){
                presupuestoPersonaltarea.add(i);
            }
        }
        return presupuestoPersonaltarea;
    }

    @GetMapping("/presupuesto/{id}")
    public List<PresupuestoPersonal> getPresupuestoById(@PathVariable int id) {
        List<PresupuestoPersonal> presupuestoPersonal = presupuestoPersonalRepository.findAll();
        List<PresupuestoPersonal> presupuestoPersonaltarea = new ArrayList<PresupuestoPersonal>();
        for (PresupuestoPersonal i : presupuestoPersonal) {
            if (i.getIdPresupuesto().getId()==id){
                presupuestoPersonaltarea.add(i);
            }
        }
        return presupuestoPersonaltarea;
    }


    @PostMapping
    public PresupuestoPersonal postPresupuestoPersonal(@RequestBody PresupuestoPersonal presupuestoPersonal) {
        presupuestoPersonalRepository.save(presupuestoPersonal);
        return presupuestoPersonal;
    }

    @PutMapping("/{idP}/{idM}")
    public PresupuestoPersonal putPresupuestoPersonalbyId(@PathVariable int idP,@PathVariable int idM, @RequestBody PresupuestoPersonal presupuestoPersonal) {
        /*
        Optional<List<PresupuestoPersonal>> presupuestoPersonalCurrent = Optional.of(presupuestoPersonalRepository.findAll());

        if (presupuestoPersonalCurrent.isPresent()) {
            List<PresupuestoPersonal> presupuestoPersonalReturn = presupuestoPersonalCurrent.get();
            List<PresupuestoPersonal> presupuestoPersonalIdReturn = presupuestoPersonalCurrent.get();

            for (PresupuestoPersonal i : presupuestoPersonalReturn) {
                if (i.getIdPresupuesto().getId()==idP&&i.getIdMaterial().getId()==idM){
                    presupuestoPersonalIdReturn.add(i);
                }
            }
            presupuestoPersonalIdReturnReturn.get(presupuestoPersonal.getIdMaterial());
            presupuestoPersonalReturn.setCantidad(presupuestoPersonal.getCantidad());
            presupuestoPersonalReturn.setCosto(presupuestoPersonal.getCosto());
            presupuestoPersonalReturn.setTiempoUso(presupuestoPersonal.getTiempoUso());

            presupuestoPersonalRepository.save(presupuestoPersonalReturn);
            return presupuestoPersonalReturn;
        }
        */
        return null;
    }


    @DeleteMapping("/{id}")
    public PresupuestoPersonal deletePresupuestoPersonalbyId(@PathVariable int id) {
    /*
            Optional<PresupuestoPersonal> presupuestoPersonal = presupuestoPersonalRepository.findById(id);

            if (presupuestoPersonal.isPresent()) {
                PresupuestoPersonal presupuestoPersonalReturn = presupuestoPersonal.get();
                presupuestoPersonalRepository.deleteById(id);
                return presupuestoPersonalReturn;
            }
    */
        return null;
    }
}