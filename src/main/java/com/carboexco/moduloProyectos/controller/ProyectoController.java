package com.carboexco.moduloProyectos.controller;
import com.carboexco.moduloProyectos.entity.*;
import com.carboexco.moduloProyectos.repository.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/busqueda/{id}")
    public List<Proyecto> getProyectobyNombre(@PathVariable String id) {

        List<Proyecto> proyecto=new ArrayList<>();
        if (id == null) {
            proyecto=proyectoRepository.findAll();
        }else {
            proyecto = proyectoRepository.findByNombreProyectoLike(id);
        }
        return proyecto;
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
            List<Tarea> tareas= tareaPersonaRepository.findByidEtapaProyecto(etapaProyectoRepository.findByidProyectoAndIdEtapa(i, etapa.get()).get());
            barrasColumnas[1] = tareas.size();
            barrasColumnas[2]= TareaController.gettareasTerminadas(tareas).size();
            barrasfilas[n]=barrasColumnas;
            n++;
        }
        return barrasfilas;
    }

    @GetMapping("/tabla")
    public Object[][] tabla() {
        List<Proyecto> proyectosEjecucion = proyectosEtapa(5);
        Object[][] tablaFilas = new Object[proyectosEjecucion.size()+1][];
        Optional<Etapa> etapa = etapaRepository.findById(5);
        Object[] barras1=new Object[3];
        barras1[0]="nombre de proyecto";
        barras1[1]="responsables";
        barras1[2]="avances";
        tablaFilas[0]=barras1;
        int n = 1;
        for (Proyecto i : proyectosEjecucion) {
            List<ProyectoPersona> proyectoP = proyectoPersonaRepository.findById_ProyectoAndId_Etapa(i.getId(), etapa.get().getId());
            if (!proyectoP.isEmpty()) {
                String nombreProyecto = i.getNombreProyecto();
                //String idsPersonas = proyectoP.stream().map(pp -> String.valueOf(pp.getId().getPersona())).collect(Collectors.joining(", "));
                float avanceProyecto = this.avanceProyecto(i, etapa.get());
                tablaFilas[n++] = new Object[] {nombreProyecto, responsables(i.getId()), avanceProyecto};
            }
        }
        return tablaFilas;
    }

    @GetMapping("/tablaProyectos/{busqueda}/")
    public Object[][] tablaProyectos(@PathVariable String busqueda) {
        List<Proyecto> proyectos=new ArrayList<>();
        try {
            if (busqueda=="*"){
            proyectos = getProyectobyNombre(null);
            }else {
                int etapa = Integer.parseInt(busqueda);
                if (etapa <= 8) {
                    proyectos = proyectosEtapa(etapa);
                } else {
                    proyectos = getProyectobyNombre(busqueda);
                }
            }
        }catch (NumberFormatException e) {
            proyectos = getProyectobyNombre(busqueda);
        }

        return tablaProyectos(proyectos);
    }

    public Object[][] tablaProyectos( List<Proyecto> proyectos) {

        if (proyectos == null || proyectos.isEmpty()){
            proyectos = proyectoRepository.findAll();}
        Object[][] tablaFilas = new Object[proyectos.size()+1][];
        Object[] barras1 = new Object[]{"Id", "Nombre de proyecto", "Reponsble", "Etapa", "Avance"};
        tablaFilas[0]=barras1;
        int n = 1;
        for (Proyecto i : proyectos) {
            List<EtapaProyecto> proyectoE = etapaProyectoRepository.findByIdProyecto_IdAndIdEstado_Id(i.getId(), 2);
            Optional<Etapa> etapa = etapaRepository.findById(proyectoE.get(0).getIdEtapa().getId());
            if(etapa.isPresent()){
            List<ProyectoPersona> proyectoP = proyectoPersonaRepository.findById_ProyectoAndId_Etapa(i.getId(), etapa.get().getId());
            String avance ="--";
            if(etapa.get().getId()==5){
                avance= this.avanceProyecto(i, etapa.get())+"%";
            }
            if(etapa.get().getId()==6){
                avance ="100%";
            }
            if (!proyectoP.isEmpty() && etapa.get().getId()<7) {
                tablaFilas[n++] = new Object[] {i.getId(), i.getNombreProyecto(),
                        responsables(i.getId()),
                        etapa.get().getNombreEtapa(),avance};
            }}
        }
        return tablaFilas;
    }

    @GetMapping("/responsable/{i}")
    public String responsables(@PathVariable int i){
        List<EtapaProyecto> proyectoE = etapaProyectoRepository.findByIdProyecto_IdAndIdEstado_Id(i, 2);
        List<ProyectoPersona> proyectoP = proyectoPersonaRepository.findById_ProyectoAndId_Etapa(i, proyectoE.get(0).getIdEtapa().getId());
        return proyectoP.stream().map(pp -> String.valueOf(pp.getId().getPersona())).collect(Collectors.joining(", "));
    }

    public String personaProyecto(@NotNull ProyectoPersona proyectoP) {
        return proyectoP.getId().getPersona().toString();
    }

    public float avanceProyecto(Proyecto i, @NotNull Etapa e){
        float rta=0f;
        if (e.getId()==5) {
            List<Tarea> tareas = tareaPersonaRepository.findByidEtapaProyecto(etapaProyectoRepository.findByidProyectoAndIdEtapa(i, e).get());
            int numerotareas = tareas.size();
            int numerotareasterminadas = TareaController.gettareasTerminadas(tareas).size();
            //System.out.println(numerotareasterminadas+" / "+numerotareas +" = "+((numerotareasterminadas / numerotareas) * 100));
            if(numerotareas!=0){
                 rta= ((float)numerotareasterminadas / numerotareas) * 100;
            }
        }
        return rta;
    }

    @GetMapping("/etapa/{idetapa}")
    public List<Proyecto> proyectosEtapa(@PathVariable int idetapa){
        List<Proyecto> proyectos=proyectoRepository.findAll();
        List <Proyecto> proyectosEjecucion= new ArrayList<>();
        if(!proyectos.isEmpty()){
        Optional<Etapa> etapa= etapaRepository.findById(idetapa);
        for (Proyecto i :proyectos) {
            Optional<EtapaProyecto> etapas = etapaProyectoRepository.findByidProyectoAndIdEtapa(i,etapa.get());
            if (etapas.isPresent()&&etapas.get().getFechaFinal()==null && etapas.get().getIdEstado().getId()==2){ proyectosEjecucion.add(i);}
        }}
        return proyectosEjecucion;
    }

    private List<Proyecto> proyectosEtapa(List<Proyecto> proyectos,int idetapa){

        List <Proyecto> proyectosEjecucion= new ArrayList<>();
        if(!proyectos.isEmpty()){
            Optional<Etapa> etapa= etapaRepository.findById(idetapa);
            for (Proyecto i :proyectos) {
                Optional<EtapaProyecto> etapas = etapaProyectoRepository.findByidProyectoAndIdEtapa(i,etapa.get());
                if (etapas.isPresent() && etapas.get().getFechaFinal()==null && etapas.get().getIdEstado().getId()==2){ proyectosEjecucion.add(i);}
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