package com.carboexco.moduloProyectos.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StatusController {
    @GetMapping({"/status"})
    public String status(){
        return "ok esto me salvo >>_<<";
    }
}
