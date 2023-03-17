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
        int pageNumber = writer.getPageNumber();

        try {
            // Agregar tabla de encabezado en las páginas posteriores
            PdfPTable table = new PdfPTable(2);
            table.setTotalWidth(350);
            //table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            Image image = Image.getInstance("c:/img/img/LOGO_CARBOEXCO.png");
            table.addCell(image);
            table.addCell("Reporte generado por: " + System.getProperty("user.name") +"\n"
                    + new Date()
            );
            if (pageNumber == 1) {
                document.setMargins(32, 0, 100, 30);
            }else{
                document.setMargins(32, 0, 100, 30);
                table.writeSelectedRows(-1, -1, 30, 978.0f, writer.getDirectContent());
            }
        } catch (BadElementException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
