package com.carboexco.moduloProyectos.controller;
import com.carboexco.moduloProyectos.entity.Etapa;
import com.carboexco.moduloProyectos.entity.EtapaProyecto;
import com.carboexco.moduloProyectos.entity.Proyecto;
import com.carboexco.moduloProyectos.entity.Tipoproyecto;
import com.carboexco.moduloProyectos.repository.EtapaProyectoRepository;
import com.carboexco.moduloProyectos.repository.EtapaRepository;
import com.carboexco.moduloProyectos.repository.ProyectoRepository;
import com.carboexco.moduloProyectos.repository.TipoproyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    ProyectoRepository proyectoRepository;
    @Autowired
    private TipoproyectoRepository tipoproyectoRepository;
    @Autowired
    private EtapaProyectoRepository etapaProyectoRepository;
    @Autowired
    private EtapaRepository etapaRepository;

    @GetMapping
    public List<Proyecto> getProyectoAll() {
        return proyectoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Proyecto getProyectobyId(@PathVariable int id) {

        Optional<Proyecto> proyecto = proyectoRepository.findById(id);

        if (proyecto.isPresent()) {
            return proyecto.get();
        }

        return null;
    }
    @GetMapping("/tipoproyecto/{id}")
    public List<Proyecto> proyectobyTipo(@PathVariable int id) {
        Optional<Tipoproyecto> tipo= tipoproyectoRepository.findById(id);
        List <Proyecto> proyectos= proyectoRepository.findByidTipoProyecto(tipo.get());
        Optional<Etapa> etapa= etapaRepository.findById(5);
        List <Proyecto> proyectosEjecucion= new ArrayList<>();
        for (Proyecto i :proyectos) {
            EtapaProyecto etapas = etapaProyectoRepository.findByidProyectoAndIdEtapa(i,etapa.get());
            if (etapas.getFechaFinal()==null && etapas.getIdEstado().getId()==2){ proyectosEjecucion.add(i);}
        }
        return proyectosEjecucion;
    }

    @PostMapping
    public Proyecto postProyecto(@RequestBody Proyecto proyecto) {
        proyectoRepository.save(proyecto);
        return proyecto;
    }

    @PutMapping("/{id}")
    public Proyecto putProyectobyId(@PathVariable int id, @RequestBody Proyecto proyecto) {

        Optional<Proyecto> proyectoCurrent = proyectoRepository.findById(id);

        if (proyectoCurrent.isPresent()) {
            Proyecto proyectoReturn = proyectoCurrent.get();

            proyectoReturn.setDescripcionProyecto(proyecto.getDescripcionProyecto());
            proyectoReturn.setNombreProyecto(proyecto.getNombreProyecto());
            proyectoReturn.setJustificacion(proyecto.getJustificacion());
            proyectoReturn.setUbicacion(proyecto.getUbicacion());
            proyectoReturn.setIdPrioridad(proyecto.getIdPrioridad());
            proyectoReturn.setIdTipoProyecto(proyecto.getIdTipoProyecto());
            proyectoReturn.setObjetivoGeneral(proyecto.getObjetivoGeneral());
            proyectoReturn.setObjetivoEspecifico(proyecto.getObjetivoEspecifico());
            proyectoRepository.save(proyectoReturn);
            return proyectoReturn;
        }

        return null;
    }


    @DeleteMapping("/{id}")
    public Proyecto deleteProyectobyId(@PathVariable int id) {

        Optional<Proyecto> proyecto = proyectoRepository.findById(id);

        if (proyecto.isPresent()) {
            Proyecto proyectoReturn = proyecto.get();
            proyectoRepository.deleteById(id);
            return proyectoReturn;
        }

        return null;
    }
}