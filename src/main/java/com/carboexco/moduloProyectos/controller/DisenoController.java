package com.carboexco.moduloProyectos.controller;

import com.carboexco.moduloProyectos.entity.Bitacora;
import com.carboexco.moduloProyectos.entity.Diseno;
import com.carboexco.moduloProyectos.repository.DisenoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/disenos")
public class DisenoController {

    @Autowired
    DisenoRepository disenoRepository;

    @GetMapping
    public List<Diseno> getDisenoAll() {
        return disenoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Diseno getDisenobyId(@PathVariable int id) {

        Optional<Diseno> diseno = disenoRepository.findById(id);

        if (diseno.isPresent()) {
            return diseno.get();
        }
        return null;
    }
    @GetMapping("/proyecto/{id}")
    public List<Diseno> getproyectoById(@PathVariable int id) {
        List<Diseno> disenos =  disenoRepository.findByIdProyecto_Id(id);
        return disenos;
    }

    @GetMapping("/{id}/{idF}")
    public Diseno putDisenoid (@PathVariable int id, @PathVariable int idF) {

        Optional<Diseno> bitacoraCurrent = disenoRepository.findById(id);

        if (bitacoraCurrent.isPresent()) {
            Diseno disenoReturn = bitacoraCurrent.get();

            disenoReturn.setIdFoto(idF);
            disenoRepository.save(disenoReturn);
            return disenoReturn;
        }
        return null;
    }

    @PostMapping
    public int postDiseno(@RequestBody Diseno diseno) {
        disenoRepository.save(diseno);
        return diseno.getId();
    }

    @PutMapping("/{id}")
    public Diseno putDisenobyId(@PathVariable int id, @RequestBody Diseno diseno) {

        Optional<Diseno> disenoCurrent = disenoRepository.findById(id);

        if (disenoCurrent.isPresent()) {
            Diseno disenoReturn = disenoCurrent.get();

            disenoReturn.setNombreDiseno(diseno.getNombreDiseno());
            disenoReturn.setFecha(diseno.getFecha());
            disenoReturn.setAreaTerreno(diseno.getAreaTerreno());
            disenoReturn.setIdFoto(diseno.getIdFoto());
            disenoReturn.setIdEstado(diseno.getIdEstado());

            disenoRepository.save(disenoReturn);
            return disenoReturn;
        }

        return null;
    }


    @DeleteMapping("/{id}")
    public Diseno deleteDisenobyId(@PathVariable int id) {

        Optional<Diseno> diseno = disenoRepository.findById(id);

        if (diseno.isPresent()) {
            Diseno disenoReturn = diseno.get();
            disenoRepository.deleteById(id);
            return disenoReturn;
        }

        return null;
    }
}