package com.carboexco.moduloProyectos.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/generador2")
public class HtmlToPdfConverter {
    public String generador2() {
        String rta;
        try {

            InputStream is = new FileInputStream("input.html");
            OutputStream os = new FileOutputStream("output.pdf");
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, os);
            document.open();
            Font font = new Font();
            font.setSize(12);
            XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, is);
            document.close();
            os.close();
            is.close();
            rta= "generado exitosamente";
        } catch (Exception e) {
            rta=e.toString();
            e.printStackTrace();
        }
        return rta;
    }
}