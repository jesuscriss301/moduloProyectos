package com.carboexco.moduloProyectos.controller;
import com.carboexco.moduloProyectos.entity.*;
import com.carboexco.moduloProyectos.repository.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private TareaRepository tareaPersonaRepository;
    @Autowired
    private ProyectoPersonaRepository proyectoPersonaRepository;

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

    @GetMapping("/circular")
    public Object[][] circular() {
        List<Tipoproyecto> tipos= tipoproyectoRepository.findAll();
        Object[][] circularfilas= new Object[tipos.size()+1][];
        int n = 1;
        Object[] circular1=new Object[2];
        circular1[0]="Tipo de proyecto";
        circular1[1]="cantidad de proyectos";
        circularfilas[0]=circular1;
        for (Tipoproyecto i : tipos) {
            List <Proyecto> p= proyectoRepository.findByidTipoProyecto(i);
            Object[] circularcolumnas=new Object[2];
            circularcolumnas[0]=i.getNombre();
            circularcolumnas[1]= proyectosEtapa(p,5).size();
            circularfilas[n]= circularcolumnas;
            n++;
        }
        return circularfilas;
    }
    @GetMapping("/barras")
    public Object[][] barras() {
        List<Proyecto> proyectosEjecucion= proyectosEtapa(5);
        Object[][] barrasfilas= new Object[proyectosEjecucion.size()+1][];
        Object[] barras1=new Object[3];
        barras1[0]="nombre de proyecto";
        barras1[1]="Tareas";
        barras1[2]="Tareas completadas";
        barrasfilas[0]=barras1;
        int n = 1;
        Optional<Etapa> etapa= etapaRepository.findById(5);
        for (Proyecto i : proyectosEjecucion) {
            Object[] barrasColumnas= new Object[3];
            barrasColumnas[0]=i.getNombreProyecto();
            List<Tarea> tareas= tareaPersonaRepository.findByidEtapaProyecto(etapaProyectoRepository.findByidProyectoAndIdEtapa(i,etapa.get()));
            barrasColumnas[1] = tareas.size();
            barrasColumnas[2]= TareaController.gettareasTerminadas(tareas).size();
            barrasfilas[n]=barrasColumnas;
            n++;
        }
        return barrasfilas;
    }
    @GetMapping("/tabla")
    public String[][] tabla() {
        List<Proyecto> proyectosEjecucion= proyectosEtapa(5);
        String[][] tablafilas= new String[proyectosEjecucion.size()][];
        int n = 0;
        Optional<Etapa> etapa= etapaRepository.findById(5);

        for (Proyecto i : proyectosEjecucion) {
            List<ProyectoPersona> proyectoP= proyectoPersonaRepository.findById_ProyectoAndId_Etapa(i.getId(),etapa.get().getId());
            //System.out.println("______________________________"+proyectoP+"___________________________________");
            if(!proyectoP.isEmpty()){

                String tablaColumnas[]=new String[3];
                tablaColumnas[0]=i.getNombreProyecto();
                tablaColumnas[1]= proyectoP.get(1).getId().getPersona().toString();
                tablaColumnas[2]=this.avanceProyecto(i,etapa.get())+"%";
                tablafilas[n++]=tablaColumnas;
                System.out.println("--------------"+proyectoP.+"_____________________");
            }
        }

        return tablafilas;
    }

    public String personaProyecto(@NotNull ProyectoPersona proyectoP) {
        return proyectoP.getId().getPersona().toString();
    }

    public double avanceProyecto(Proyecto i, Etapa e){
        if (e.getId()==5) {
            List<Tarea> tareas = tareaPersonaRepository.findByidEtapaProyecto(etapaProyectoRepository.findByidProyectoAndIdEtapa(i, e));
            int numerotareas = tareas.size();
            int numerotareasterminadas = TareaController.gettareasTerminadas(tareas).size();
            return numerotareasterminadas / numerotareas * 100;
        }else return 0f;
    }


    @GetMapping("/{idetapa}")
    public List<Proyecto> proyectosEtapa(@PathVariable int idetapa){
        List<Proyecto> proyectos=proyectoRepository.findAll();
        List <Proyecto> proyectosEjecucion= new ArrayList<>();
        if(!proyectos.isEmpty()){
        Optional<Etapa> etapa= etapaRepository.findById(idetapa);
        for (Proyecto i :proyectos) {
            EtapaProyecto etapas = etapaProyectoRepository.findByidProyectoAndIdEtapa(i,etapa.get());
            if (etapas.getFechaFinal()==null && etapas.getIdEstado().getId()==2){ proyectosEjecucion.add(i);}
        }}
        return proyectosEjecucion;
    }
    private List<Proyecto> proyectosEtapa (List<Proyecto> proyectos,int idetapa){

        List <Proyecto> proyectosEjecucion= new ArrayList<>();
        if(!proyectos.isEmpty()){
            Optional<Etapa> etapa= etapaRepository.findById(idetapa);
            System.out.println(etapa);
            for (Proyecto i :proyectos) {
                EtapaProyecto etapas = etapaProyectoRepository.findByidProyectoAndIdEtapa(i,etapa.get());
                if (etapas.getFechaFinal()==null && etapas.getIdEstado().getId()==2){ proyectosEjecucion.add(i);}
            }}
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