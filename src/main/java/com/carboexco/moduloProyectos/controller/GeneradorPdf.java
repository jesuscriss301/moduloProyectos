package com.carboexco.moduloProyectos.controller;

import com.carboexco.moduloProyectos.entity.*;
import com.carboexco.moduloProyectos.repository.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/generador")
public class GeneradorPdf {
    private static final String FILE = "c:/img/img/Informeproyecto.pdf";
    private static final Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static final Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static final Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private static final Font small = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL);

    private Proyecto proyecto;
    @Autowired
    ProyectoRepository proyectoRepository;
    @Autowired
    private ProyectoPersonaRepository proyectoPersonaRepository;
    @Autowired
    private DisenoRepository disenoRepository;
    @Autowired
    private PresupuestoRepository presupuestoRepository;
    @Autowired
    private TareaRepository tareaRepository;

    private ArrayList<Integer> concatenar;
    @Autowired
    private PresupuestoMaterialRepository presupuestoMaterialRepository;
    @Autowired
    private TareaPersonaRepository tareaPersonaRepository;

    @GetMapping("/pdf/{id}")
    public String genearPdf(@PathVariable int id) {

        Optional<Proyecto> nuevo = proyectoRepository.findById(id);
        try {
            if (nuevo.isPresent()) {
                this.proyecto = nuevo.get();
                Document document = new Document();
                document.setPageSize(PageSize.LEGAL);
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));

                Header header = new Header();
                writer.setPageEvent(header);
                document.open();
                addMetaData(document);
                addTitlePage(document);
                addContent(document);
                document.close();
                concatenarpdf();
                return "generado exitosamente";
            } else {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            e.getStackTrace();
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void addConcatenarDiseno(Document document, PdfWriter writer, String direccion) throws IOException {
        ArrayList<Diseno> disenos = (ArrayList<Diseno>) disenoRepository.findByIdProyecto_IdAndIdEstado_Id(proyecto.getId(), 1);
        for (Diseno i : disenos) {

            PdfReader reader = new PdfReader("documento_existente.pdf");

            int numPages = reader.getNumberOfPages();

            for (int j = 1; j <= numPages; j++) {
                PdfImportedPage page = writer.getImportedPage(reader, j);
                document.newPage();
                writer.getDirectContent().addTemplate(page, 0, 0);
            }
        }
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private void addMetaData(@NotNull Document document) {
        document.addTitle("Informe de proyeto");
        document.addSubject("Informe de proyecto: " + proyecto.getNombreProyecto());
        document.addKeywords("Carboexco, Informe, Modulo de proyectos");
        document.addAuthor("Carboexco");
        document.addCreator("Departamento de sistemas Carboexco");
    }

    private static void addTitlePage(@NotNull Document document) throws DocumentException {

        // Let's write a big header
        Anchor anchor = new Anchor("Informe de proyecto ", catFont);
        anchor.setName("Informe de proyecto ");// TITULO DE LA HOJA

        //Chapter catPart = new Chapter(new Paragraph(anchor), 1);
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        Paragraph subPara = new Paragraph("Reporte generado por: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold);

        subPara.add(new Paragraph("Informe de proyeto", catFont));

        addEmptyLine(subPara, 1);
        // Will create: Report generated by: _name, _date

        addEmptyLine(subPara, 3);
        subPara.add(new Paragraph(
                "informe de prueba generado par o computadora",
                smallBold));

        addEmptyLine(subPara, 8);

        subPara.add(new Paragraph(
                "la informacion vista en este documento no es real esto es un bosquejo.",
                redFont));

        document.add(subPara);
        // Start a new page
        document.newPage();
    }

    private void addContent(@NotNull Document document) throws DocumentException, IOException {
        // now add all this to the document
        document.add(indice());
        document.add(contenido()); //AGREGAR DATOS AL DOCUMENTO
    }

    private @NotNull Chapter indice() throws DocumentException {

        Anchor anchor = new Anchor("Indicé informe de proyecto " + this.proyecto.getNombreProyecto(), catFont);
        anchor.setName("Indicé informe de proyecto " + this.proyecto.getNombreProyecto());// TITULO DE LA HOJA

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
        Paragraph paragraph = new Paragraph();
        //addEmptyLine(paragraph,1);

        Paragraph conceptual = new Paragraph("Información del proyecto", subFont);
        Section subConceptual = catPart.addSection(conceptual);

        //Add a list
        createListConceptual(subConceptual);
        subConceptual.add(paragraph);

        //Tareas
        ArrayList<Tarea> listTareasProyecto = (ArrayList<Tarea>) tareaRepository.findByIdEtapaProyecto_IdProyecto_Id(proyecto.getId());
        Paragraph tarea = new Paragraph("Tareas " + listTareasProyecto.size(), subFont);
        Section subTarea = catPart.addSection(tarea);
        Section tareasCompletadas = subTarea.addSection("Tareas completadas: " + TareaController.gettareasTerminadas(listTareasProyecto).size() + "/" + listTareasProyecto.size());
        createListTareasCompletadas(listTareasProyecto, tareasCompletadas, true);
        Section tareasEjecucion = subTarea.addSection("Tareas en ejecución: " + TareaController.getTareasEnEjecucion(listTareasProyecto).size() + "/" + listTareasProyecto.size());
        createListTareasEnEjecucion(listTareasProyecto, tareasEjecucion, true);
        Section tareasEspera = subTarea.addSection("Tareas en espera: " + TareaController.getTareasEnEspera(listTareasProyecto).size() + "/" + listTareasProyecto.size());
        createListTareasEnEspera(listTareasProyecto, tareasEspera, true);

        //Presupuesto
        Paragraph presupuesto = new Paragraph("Presupuestos del proyecto", subFont);
        Section subpresupuesto = catPart.addSection(presupuesto);
        createListPresupuesto(subpresupuesto);

        //Diseño
        Paragraph diseno = new Paragraph("Diseño del proyecto", subFont);
        Section subdiseno = catPart.addSection(diseno);
        createListDiseno(subdiseno);

        return catPart;
    }

    private @NotNull Chapter contenido() throws DocumentException, IOException {
        Paragraph paragraph = new Paragraph();
        // Next section
        int n = 1;
        Anchor anchor = new Anchor("Informe de proyeto", catFont);// Subtitulo
        anchor.setName("Informe de proyeto");
        Chapter catPart = new Chapter(new Paragraph(anchor), n);
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1); //LINEA EN BLANCO
        ArrayList<ProyectoPersona> proyectoPersona = (ArrayList<ProyectoPersona>) proyectoPersonaRepository.findById_Proyecto(proyecto.getId());
        String responsables = proyectoPersona.stream()
                .map(pp -> String.valueOf(pp.getId().getPersona()))
                .distinct()
                .collect(Collectors.joining(", ")); //RESPONSABLES DEL PROYECTO
        preface.add(new Paragraph(
                "Proyecto " + proyecto.getIdTipoProyecto().getNombre() + " #" + proyecto.getId() + " "
                        + proyecto.getNombreProyecto() + " de prioridad " + proyecto.getIdPrioridad().getNombrePrioridad() +
                        ", responsables del proyecto " + responsables + "."
                , small)); //PARRAFO INFORMACION DEL PROYECTO
        addEmptyLine(preface, 1); //LINEA EN BLANCO
        catPart.add(preface); //AGREGAR PARRAFO AL DOCUMENTO

        Paragraph subPara = new Paragraph("Información del proyecto", subFont);
        Section seccionConceptual = catPart.addSection(subPara);

        subPara = new Paragraph("Descripción del proyecto:");//SUBTITULO
        Section subCatPart = seccionConceptual.addSection(subPara);
        subCatPart.add(new Paragraph("        " + this.proyecto.getDescripcionProyecto())); //INFORMACION DESCRIPCION
        //subCatPart.add(paragraph);

        if (proyecto.getJustificacion() != null) {
            subPara = new Paragraph("Justificación del proyecto:");//SUBTITULO
            subCatPart = seccionConceptual.addSection(subPara);
            subCatPart.add(new Paragraph("        " + this.proyecto.getJustificacion()));//INFORMACION DESCRIPCION
            subCatPart.add(paragraph);
        }
        if (proyecto.getObjetivoGeneral() != null) {

            subPara = new Paragraph("Objetivo general del proyecto:");//SUBTITULO
            subCatPart = seccionConceptual.addSection(subPara);
            subCatPart.add(new Paragraph("        " + this.proyecto.getObjetivoGeneral()));//INFORMACION DESCRIPCION
            subCatPart.add(paragraph);
        }
        if (proyecto.getObjetivoEspecifico() != null) {

            subPara = new Paragraph("Objetivo especifico del proyecto:");//SUBTITULO
            subCatPart = seccionConceptual.addSection(subPara);
            subCatPart.add(new Paragraph("        " + this.proyecto.getObjetivoEspecifico()));//INFORMACION DESCRIPCION
            subCatPart.add(paragraph);
        }

        //Tareas
        ArrayList<Tarea> listTareasProyecto = (ArrayList<Tarea>) tareaRepository.findByIdEtapaProyecto_IdProyecto_Id(proyecto.getId());
        Paragraph tarea = new Paragraph("Tareas " + listTareasProyecto.size(), subFont);
        Section subTarea = catPart.addSection(tarea);
        Section tareasCompletadas = subTarea.addSection("Tareas completadas: " + TareaController.gettareasTerminadas(listTareasProyecto).size() + "/" + listTareasProyecto.size());
        createListTareasCompletadas(listTareasProyecto, tareasCompletadas, false);
        Section tareasEjecucion = subTarea.addSection("Tareas en ejecución: " + TareaController.getTareasEnEjecucion(listTareasProyecto).size() + "/" + listTareasProyecto.size());
        createListTareasEnEjecucion(listTareasProyecto, tareasEjecucion, false);
        Section tareasEspera = subTarea.addSection("Tareas en espera: " + TareaController.getTareasEnEspera(listTareasProyecto).size() + "/" + listTareasProyecto.size());
        createListTareasEnEspera(listTareasProyecto, tareasEspera, false);

        //Presupuesto
        Paragraph presupuesto = new Paragraph("Presupuestos del proyecto", subFont);
        Section subProyecto = catPart.addSection(presupuesto);
        ArrayList<Presupuesto> presupuestoAprovados = (ArrayList<Presupuesto>) presupuestoRepository.findByIdProyecto_IdAndIdEstado_Id(proyecto.getId(), 1);
        for (Presupuesto i : presupuestoAprovados) {
            Section material = subProyecto.addSection(new ListItem("IdPresupuesto: #" + i.getId() + " ($" + i.getCostoTotal() + ")"));
            ArrayList<PresupuestoMaterial> materials = (ArrayList<PresupuestoMaterial>) presupuestoMaterialRepository.findByIdPresupuesto_Id(i.getId());
            createTableMaterials(material, materials);
        }

        //Diseño
        Paragraph diseno = new Paragraph("Diseño", subFont);
        Section subDiseno = catPart.addSection(diseno);
        //this.createListDiseno(subDiseno);
        ArrayList<Diseno> disenosAprovados = (ArrayList<Diseno>) disenoRepository.findByIdProyecto_IdAndIdEstado_Id(proyecto.getId(), 1);
        for (Diseno i : disenosAprovados) {
            subDiseno.addSection(new ListItem(i.getNombreDiseno()));
            this.concatenar = new ArrayList<>();
            this.concatenar.add(i.getIdFoto());
        }

        return catPart;
    }

    public void createTableMaterials(@NotNull Section subCatPart, @NotNull ArrayList<PresupuestoMaterial> listTareasProyecto) throws DocumentException {

        subCatPart.add(new Paragraph("  "));

        PdfPTable table = new PdfPTable(5);
        float[] columnWidths = {50f, 50f, 50f, 50f, 50f};
        table.setWidths(columnWidths);
        //table.setTotalWidth(PageSize.A4.getWidth() * 0.8f);
        PdfPCell c1 = new PdfPCell(new Phrase("Tipo"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nombre del Material"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Cantidad"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Costo"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Tiempo de uso"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        for (PresupuestoMaterial i : listTareasProyecto) {

            table.addCell(i.getIdMaterial().getTipoMaterial());
            table.addCell(i.getIdMaterial().getIdProducto() + "");
            table.addCell(i.getCosto() + "");
            table.addCell(i.getCantidad() + "");
            table.addCell(i.getTiempoUso() + "");
        }
        //subCatPart.setIndentationRight(200);
        subCatPart.add(table);
        subCatPart.setIndentation(-36f);
    }

    private static void createTable(@NotNull Section subCatPart, @NotNull ArrayList<Tarea> listTareasProyecto) throws DocumentException {

        subCatPart.add(new Paragraph("  "));

        PdfPTable table = new PdfPTable(4);
        float[] columnWidths = {50f, 100f, 50f, 100f};
        table.setWidths(columnWidths);
        //table.setTotalWidth(PageSize.A4.getWidth() * 0.8f);
        PdfPCell c1 = new PdfPCell(new Phrase("Nombre Tarea"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Fecha de inicio \n Fecha de termino"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Etapa"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Descripcion Tarea"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        for (Tarea i : listTareasProyecto) {

            table.addCell(i.getNombreTarea());
            if (i.getFechaFinalReal() == null && i.getFechaInicioReal() != null) {
                table.addCell(i.getFechaInicioReal() + "\n" + "En ejecución");
            } else if (i.getFechaFinalReal() == null && i.getFechaInicioReal() == null) {
                table.addCell("(fechas programadas) \n" + i.getFechaInicio() + "\n" + i.getFechaFinal());
            } else {
                table.addCell(i.getFechaInicioReal() + "\n" + i.getFechaFinalReal());
            }
            table.addCell(i.getIdEtapaProyecto().getIdEtapa().getNombreEtapa());
            table.addCell(i.getDescripcionTarea());
        }
        //subCatPart.setIndentationRight(200);
        subCatPart.add(table);
        subCatPart.setIndentation(-36f);
    }

    private void createListConceptual(Section subCatPart) {

        if (this.proyecto.getDescripcionProyecto() != null) {
            subCatPart.addSection(new ListItem("Descripción del proyecto"));
        }
        if (proyecto.getJustificacion() != null) {
            subCatPart.addSection(new ListItem("Justificación del proyecto"));
        }
        if (proyecto.getObjetivoGeneral() != null) {
            subCatPart.addSection(new ListItem("Objetivo general del proyecto"));
        }
        if (proyecto.getObjetivoEspecifico() != null) {
            subCatPart.addSection(new ListItem("Objetivo especifico del proyecto"));
        }
    }

    private void createListTareasEnEspera(ArrayList<Tarea> listTareasProyecto, Section subCatPart, boolean indice) throws DocumentException {
        List list = new List(false, true, 20);
        ArrayList<Tarea> listTareasProyecto1 = (ArrayList<Tarea>) TareaController.getTareasEnEspera(listTareasProyecto);
        if (!indice) {
            createTable(subCatPart, listTareasProyecto1);
        } else {
            for (Tarea i : listTareasProyecto1) {
                list.add(new ListItem(i.getNombreTarea()));
            }
        }

        subCatPart.add(list);
    }

    private void createListTareasCompletadas(ArrayList<Tarea> listTareasProyecto, Section subCatPart, boolean indice) throws DocumentException {
        List list = new List(false, true, 20);
        ArrayList<Tarea> listTareasProyecto1 = (ArrayList<Tarea>) TareaController.gettareasTerminadas(listTareasProyecto);
        if (!indice) {
            createTable(subCatPart, listTareasProyecto1);
        } else {
            for (Tarea i : listTareasProyecto1) {
                list.add(new ListItem(i.getNombreTarea()));
            }
        }
        subCatPart.add(list);
    }

    private void createListTareasEnEjecucion(ArrayList<Tarea> listTareasProyecto, Section subCatPart, boolean indice) throws DocumentException {
        List list = new List(false, true, 20);
        ArrayList<Tarea> listTareasProyecto1 = (ArrayList<Tarea>) TareaController.getTareasEnEjecucion(listTareasProyecto);
        if (!indice) {
            createTable(subCatPart, listTareasProyecto1);
        } else {
            for (Tarea i : listTareasProyecto1) {
                list.add(new ListItem(i.getNombreTarea()));
            }
        }

        subCatPart.add(list);
    }

    private void createListDiseno(Section subCatPart) {
        ArrayList<Diseno> disenosAprovados = (ArrayList<Diseno>) disenoRepository.findByIdProyecto_IdAndIdEstado_Id(proyecto.getId(), 1);
        for (Diseno i : disenosAprovados) {
            subCatPart.addSection(new ListItem(i.getNombreDiseno()));
        }
    }

    private void createListPresupuesto(Section subProyecto) {
        ArrayList<Presupuesto> presupuestoAprovados = (ArrayList<Presupuesto>) presupuestoRepository.findByIdProyecto_IdAndIdEstado_Id(proyecto.getId(), 1);
        for (Presupuesto i : presupuestoAprovados) {
            subProyecto.addSection(new ListItem("IdPresupuesto: #" + i.getId() + " ($" + i.getCostoTotal() + ")"));
        }
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private void concatenarpdf() {

        try {
            ArrayList<String> rta = new ArrayList<>();
            rta.add(FILE);
            for (int i : concatenar) {
                // URL del recurso a consultar
                URL url = new URL("http://localhost:8081/data/files/direccion/" + i);
                // Abrimos la conexión con la API REST
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                // Establecemos el método HTTP a utilizar (GET, POST, PUT, DELETE, etc.)
                con.setRequestMethod("GET");
                // Leemos la respuesta de la API REST
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String linea;
                StringBuilder respuesta = new StringBuilder();
                while ((linea = in.readLine()) != null) {
                    respuesta.append(linea);
                }
                rta.add(respuesta.toString());
                in.close();
            }

            Document pdfDocument = new Document();
            FileOutputStream pdfOutputStream = new FileOutputStream("c:/img/img/archivo_resultante.pdf");
            PdfCopy copy = new PdfCopy(pdfDocument, pdfOutputStream);

            pdfDocument.open();
            System.out.println(rta);

            for (String file : rta) {
                if (file.contains(".pdf")) {
                    /* Copy all the pages in the PDF file into the new PDF */
                    PdfReader reader = new PdfReader(file);
                    for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                        copy.addPage(copy.getImportedPage(reader, i));
                    }
                }
            }
            pdfDocument.close();
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
