package com.carboexco.moduloProyectos.controller;
import com.carboexco.moduloProyectos.entity.Bitacora;
import com.carboexco.moduloProyectos.repository.BitacoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/bitacoras")
public class BitacoraController {

    @Autowired
    BitacoraRepository bitacoraRepository;

    @GetMapping
    public List<Bitacora> getBitacoraAll() {
        return bitacoraRepository.findAll();
    }

    @GetMapping("/tarea/{id}")
    public List<Bitacora> getTareaById(@PathVariable int id) {
        List<Bitacora> bitacora = bitacoraRepository.findAll();
        List<Bitacora> bitacoratarea = new ArrayList<Bitacora>();
        for (Bitacora i : bitacora) {
            if (i.getIdTarea().getId()==id){
                bitacoratarea.add(i);
            }
        }
        return bitacoratarea;
    }

    @GetMapping("/{id}")
    public Bitacora getBitacorabyId(@PathVariable int id) {

        Optional<Bitacora> bitacora = bitacoraRepository.findById(id);

        if (bitacora.isPresent()) {
            return bitacora.get();
        }

        return null;
    }

    @PostMapping
    public Bitacora postBitacora(@RequestBody Bitacora bitacora) {
        bitacoraRepository.save(bitacora);
        return bitacora;
    }

    @PutMapping("/{id}")
    public Bitacora putBitacorabyId(@PathVariable int id, @RequestBody Bitacora bitacora) {

        Optional<Bitacora> bitacoraCurrent = bitacoraRepository.findById(id);

        if (bitacoraCurrent.isPresent()) {
            Bitacora bitacoraReturn = bitacoraCurrent.get();

            bitacoraReturn.setDescripcionBitacora(bitacora.getDescripcionBitacora());
            bitacoraReturn.setObservacionBitacora(bitacora.getObservacionBitacora());
            bitacoraReturn.setDescripcionBitacora(bitacora.getDescripcionBitacora());
            bitacoraReturn.setFechaHora(bitacora.getFechaHora());
            bitacoraReturn.setFileFoto(bitacora.getFileFoto());

            bitacoraRepository.save(bitacoraReturn);
            return bitacoraReturn;
        }

        return null;
    }


    @DeleteMapping("/{id}")
    public Bitacora deleteBitacorabyId(@PathVariable int id) {

        Optional<Bitacora> bitacora = bitacoraRepository.findById(id);

        if (bitacora.isPresent()) {
            Bitacora bitacoraReturn = bitacora.get();
            bitacoraRepository.deleteById(id);
            return bitacoraReturn;
        }

        return null;
    }
}