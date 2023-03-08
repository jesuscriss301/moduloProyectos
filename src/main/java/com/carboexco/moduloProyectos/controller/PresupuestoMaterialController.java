package com.carboexco.moduloProyectos.controller;
import com.carboexco.moduloProyectos.entity.PresupuestoMaterial;
import com.carboexco.moduloProyectos.repository.PresupuestoMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/presupuestoMaterials")
public class PresupuestoMaterialController {

    @Autowired
    PresupuestoMaterialRepository presupuestoMaterialRepository;

    @GetMapping
    public List<PresupuestoMaterial> getPresupuestoMaterialAll() {
        return presupuestoMaterialRepository.findAll();
    }

    @GetMapping("/proyecto/{id}")
    public List<PresupuestoMaterial> getProyectoById(@PathVariable int id) {
        List<PresupuestoMaterial> presupuestoMaterial = presupuestoMaterialRepository.findAll();
        List<PresupuestoMaterial> presupuestoMaterialtarea = new ArrayList<PresupuestoMaterial>();
        for (PresupuestoMaterial i : presupuestoMaterial) {
            if (i.getIdPresupuesto().getIdProyecto().getId()==id){
                presupuestoMaterialtarea.add(i);
            }
        }
        return presupuestoMaterialtarea;
    }

    @GetMapping("/presupuesto/{id}")
    public List<PresupuestoMaterial> getPresupuestoById(@PathVariable int id) {
        List<PresupuestoMaterial> presupuestoMaterial = presupuestoMaterialRepository.findAll();
        List<PresupuestoMaterial> presupuestoMaterialtarea = new ArrayList<PresupuestoMaterial>();
        for (PresupuestoMaterial i : presupuestoMaterial) {
            if (i.getIdPresupuesto().getId()==id){
                presupuestoMaterialtarea.add(i);
            }
        }
        return presupuestoMaterialtarea;
    }


    @PostMapping
    public PresupuestoMaterial postPresupuestoMaterial(@RequestBody PresupuestoMaterial presupuestoMaterial) {
        presupuestoMaterialRepository.save(presupuestoMaterial);
        return presupuestoMaterial;
    }

    @PutMapping("/{idP}/{idM}")
    public PresupuestoMaterial putPresupuestoMaterialbyId(@PathVariable int idP,@PathVariable int idM, @RequestBody PresupuestoMaterial presupuestoMaterial) {
        /*
        Optional<List<PresupuestoMaterial>> presupuestoMaterialCurrent = Optional.of(presupuestoMaterialRepository.findAll());

        if (presupuestoMaterialCurrent.isPresent()) {
            List<PresupuestoMaterial> presupuestoMaterialReturn = presupuestoMaterialCurrent.get();
            List<PresupuestoMaterial> presupuestoMaterialIdReturn = presupuestoMaterialCurrent.get();

            for (PresupuestoMaterial i : presupuestoMaterialReturn) {
                if (i.getIdPresupuesto().getId()==idP&&i.getIdMaterial().getId()==idM){
                    presupuestoMaterialIdReturn.add(i);
                }
            }
            presupuestoMaterialIdReturnReturn.get(presupuestoMaterial.getIdMaterial());
            presupuestoMaterialReturn.setCantidad(presupuestoMaterial.getCantidad());
            presupuestoMaterialReturn.setCosto(presupuestoMaterial.getCosto());
            presupuestoMaterialReturn.setTiempoUso(presupuestoMaterial.getTiempoUso());

            presupuestoMaterialRepository.save(presupuestoMaterialReturn);
            return presupuestoMaterialReturn;
        }
        */
        return null;
    }


    @DeleteMapping("/{id}")
    public PresupuestoMaterial deletePresupuestoMaterialbyId(@PathVariable int id) {
    /*
            Optional<PresupuestoMaterial> presupuestoMaterial = presupuestoMaterialRepository.findById(id);

            if (presupuestoMaterial.isPresent()) {
                PresupuestoMaterial presupuestoMaterialReturn = presupuestoMaterial.get();
                presupuestoMaterialRepository.deleteById(id);
                return presupuestoMaterialReturn;
            }
    */
        return null;
    }
}