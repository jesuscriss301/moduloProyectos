package com.carboexco.moduloProyectos.controller;

import com.carboexco.moduloProyectos.entity.Bitacora;
import com.carboexco.moduloProyectos.entity.Etapa;
import com.carboexco.moduloProyectos.repository.BitacoraRepository;
import com.carboexco.moduloProyectos.repository.EtapaRepository;
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
    @Autowired
    private EtapaRepository etapaRepository;

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

    @GetMapping("tarea/{idT}/{idE}")
    public List<Bitacora> getBitacoraTareaEtapa(@PathVariable int idT,@PathVariable int idE) {

        Optional<Etapa> etapa= etapaRepository.findById(idE);
        if (etapa.isPresent()) {
            List<Bitacora> bitacoras = bitacoraRepository.findByIdTarea_IdAndIdTarea_IdEtapaProyecto_IdEtapa(idT, etapa.get());
            return bitacoras;
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