package com.carboexco.moduloProyectos.controller;

import com.carboexco.moduloProyectos.entity.Proyecto;
import com.carboexco.moduloProyectos.repository.ProyectoRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/generador")
public class GeneradorPdf {
    private static String FILE = "c:/img/img/Informeproyecto.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    private Proyecto proyecto;
    @Autowired
    ProyectoRepository proyectoRepository;

    @GetMapping("/pdf/{id}")
    public String genearPdf(@PathVariable int id) {
        try {
            Optional<Proyecto> nuevo = proyectoRepository.findById(id);

            if (nuevo.isPresent()){

                this.proyecto=nuevo.get();
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(FILE));
                document.open();
                addMetaData(document);
                addTitlePage(document);
                addContent(document);
                document.close();
            return "generado";}
            else {
                throw new Exception();
            }
        } catch (Exception e) {
            return "error";
        }

    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("Informe de proyeto");
        document.addSubject("Informe de proyecto: "+ "NOMBRE DE PROYECTO");
        document.addKeywords("Carboexco, Informe, Modulo de proyectos");
        document.addAuthor("Carboexco");
        document.addCreator("Lars Vogel");
    }

    private static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Informe de proyeto", catFont));

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Reporte generado por: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 3);
        preface.add(new Paragraph(
                "informe de prueba generado par o computadora",
                smallBold));

        addEmptyLine(preface, 8);

        preface.add(new Paragraph(
                "la informacion vista en este documento no es real esto es un bosquejo.",
                redFont));

        document.add(preface);
        // Start a new page
        document.newPage();
    }

    private void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("Informe de proyecto "+ this.proyecto.getNombreProyecto(), catFont);
        anchor.setName("Informe de proyecto "+ this.proyecto.getNombreProyecto());

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Indicé", subFont);
        Section subCatPart = catPart.addSection(subPara);
        int n=1;
        if(proyecto.getDescripcionProyecto()!=null){
            subCatPart.add(new Paragraph(n + "Descripción del proyecto"));
            n++;
        }
        if(proyecto.getJustificacion()!=null){
            subCatPart.add(new Paragraph(n + "Justificación del proyecto"));
            n++;
        }
        if(proyecto.getObjetivoGeneral()!=null){
            subCatPart.add(new Paragraph(n + "Objetivo general del proyecto"));
            n++;
        }
        if(proyecto.getDescripcionProyecto()!=null){
            subCatPart.add(new Paragraph(n + "Objetivo especifico del proyecto"));
            n++;
        }

        subPara = new Paragraph("Subcategory 2", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Hello"));
        // add a list
        createList(subCatPart);
        System.out.println(subCatPart);
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        subCatPart.add(paragraph);

        // add a table
        createTable(subCatPart);

        // now add all this to the document
        document.add(catPart);

        // Next section
        anchor = new Anchor("Second Chapter", catFont);
        anchor.setName("Second Chapter");

        // Second parameter is the number of the chapter
        catPart = new Chapter(new Paragraph(anchor), 1);

        subPara = new Paragraph("Subcategory", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("This is a very important message"));

        // now add all this to the document
        document.add(catPart);

    }

    private static void createTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 2"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 3"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("2.1");
        table.addCell("2.2");
        table.addCell("2.3");

        subCatPart.add(table);

    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
