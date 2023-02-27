package com.carboexco.moduloProyectos.controller;
import com.carboexco.moduloProyectos.entity.Material;
import com.carboexco.moduloProyectos.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/materials")
public class MaterialController {

    @Autowired
    MaterialRepository materialRepository;

    @GetMapping
    public List<Material> getMaterialAll() {
        return materialRepository.findAll();
    }

    @GetMapping("/tipo/{tipomaterial}")
    public List<Material> getMaterialAll(@PathVariable int tipomaterial) {
        List<Material> material = materialRepository.findAll();
        List<Material> materialtarea = new ArrayList<Material>();
        for (Material i : material) {
            if (i.getTipoMaterial().equals( tipomaterial)){
                materialtarea.add(i);
            }
        }
        return materialtarea;
    }

    @GetMapping("/{id}")
    public Material getMaterialbyId(@PathVariable int id) {

        Optional<Material> material = materialRepository.findById(id);

        if (material.isPresent()) {
            return material.get();
        }

        return null;
    }

    @PostMapping
    public Material postMaterial(@RequestBody Material material) {
        materialRepository.save(material);
        return material;
    }

    @PutMapping("/{id}")
    public Material putMaterialbyId(@PathVariable int id, @RequestBody Material material) {

        Optional<Material> materialCurrent = materialRepository.findById(id);

        if (materialCurrent.isPresent()) {
            Material materialReturn = materialCurrent.get();

            materialReturn.setTipoMaterial(material.getTipoMaterial());
            materialReturn.setIdProducto(material.getIdProducto());

            materialRepository.save(materialReturn);
            return materialReturn;
        }

        return null;
    }


    @DeleteMapping("/{id}")
    public Material deleteMaterialbyId(@PathVariable int id) {

        Optional<Material> material = materialRepository.findById(id);

        if (material.isPresent()) {
            Material materialReturn = material.get();
            materialRepository.deleteById(id);
            return materialReturn;
        }

        return null;
    }
}