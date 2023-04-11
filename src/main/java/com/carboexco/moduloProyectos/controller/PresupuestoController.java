package com.carboexco.moduloProyectos.controller;

import com.carboexco.moduloProyectos.entity.Presupuesto;
import com.carboexco.moduloProyectos.repository.PresupuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/presupuestos")
public class PresupuestoController {

    @Autowired
    PresupuestoRepository presupuestoRepository;

    @GetMapping
    public List<Presupuesto> getPresupuestoAll() {
        return presupuestoRepository.findAll();
    }

    @GetMapping("/proyecto/{id}")
    public List<Presupuesto> getPresupuestoAll(@PathVariable int id) {
        List<Presupuesto> presupuesto = presupuestoRepository.findAll();
        List<Presupuesto> presupuestoproyecto = new ArrayList<Presupuesto>();
        for (Presupuesto i : presupuesto) {
            if (i.getIdProyecto().getId()==id){
                presupuestoproyecto.add(i);
            }
        }
        return presupuestoproyecto;
    }

    @GetMapping("/{id}")
    public Presupuesto getPresupuestobyId(@PathVariable int id) {

        Optional<Presupuesto> presupuesto = presupuestoRepository.findById(id);

        if (presupuesto.isPresent()) {
            return presupuesto.get();
        }

        return null;
    }

    @PostMapping
    public Presupuesto postPresupuesto(@RequestBody Presupuesto presupuesto) {
        presupuestoRepository.save(presupuesto);
        return presupuesto;
    }

    @PutMapping("/{id}")
    public Presupuesto putPresupuestobyId(@PathVariable int id, @RequestBody Presupuesto presupuesto) {

        Optional<Presupuesto> presupuestoCurrent = presupuestoRepository.findById(id);

        if (presupuestoCurrent.isPresent()) {
            Presupuesto presupuestoReturn = presupuestoCurrent.get();

            presupuestoReturn.setIdEstado(presupuesto.getIdEstado());
            presupuestoReturn.setCostoTotal(presupuesto.getCostoTotal());

            presupuestoRepository.save(presupuestoReturn);
            return presupuestoReturn;
        }

        return null;
    }


    @DeleteMapping("/{id}")
    public Presupuesto deletePresupuestobyId(@PathVariable int id) {

        Optional<Presupuesto> presupuesto = presupuestoRepository.findById(id);

        if (presupuesto.isPresent()) {
            Presupuesto presupuestoReturn = presupuesto.get();
            presupuestoRepository.deleteById(id);
            return presupuestoReturn;
        }

        return null;
    }
}