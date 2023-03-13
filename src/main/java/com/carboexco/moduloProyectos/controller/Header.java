package com.carboexco.moduloProyectos.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Date;

public class Header extends PdfPageEventHelper {

    public void onEndPage(PdfWriter writer, Document document) {

        try {
            PdfPTable table = new PdfPTable(2);
            Paragraph nuevo = new Paragraph("\n  \n    \n");
            table.setTotalWidth(350);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            Image image = Image.getInstance("c:/img/img/LOGO_CARBOEXCO.png");
            table.addCell(image);
            table.addCell("Reporte generado por: " + System.getProperty("user.name") +"\n"
                    + new Date()
            );
            table.writeSelectedRows(-1, -1, 36, document.top() , writer.getDirectContent());


        } catch (BadElementException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
