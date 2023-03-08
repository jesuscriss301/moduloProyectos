package com.carboexco.moduloProyectos.controller;

import com.carboexco.moduloProyectos.entity.Personal;
import com.carboexco.moduloProyectos.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/personals")
public class PersonalController {

    @Autowired
    PersonalRepository personalRepository;

    @GetMapping
    public List<Personal> getPersonalAll() {
        return personalRepository.findAll();
    }

    @GetMapping("/{id}")
    public Personal getPersonalbyId(@PathVariable int id) {

        Optional<Personal> personal = personalRepository.findById(id);

        if (personal.isPresent()) {
            return personal.get();
        }

        return null;
    }

    @PostMapping
    public Personal postPersonal(@RequestBody Personal personal) {
        personalRepository.save(personal);
        return personal;
    }

    @PutMapping("/{id}")
    public Personal putPersonalbyId(@PathVariable int id, @RequestBody Personal personal) {

        Optional<Personal> personalCurrent = personalRepository.findById(id);

        if (personalCurrent.isPresent()) {
            Personal personalReturn = personalCurrent.get();

            personalReturn.setIdCargo(personal.getIdCargo());

            personalRepository.save(personalReturn);
            return personalReturn;
        }

        return null;
    }


    @DeleteMapping("/{id}")
    public Personal deletePersonalbyId(@PathVariable int id) {

        Optional<Personal> personal = personalRepository.findById(id);

        if (personal.isPresent()) {
            Personal personalReturn = personal.get();
            personalRepository.deleteById(id);
            return personalReturn;
        }

        return null;
    }
}