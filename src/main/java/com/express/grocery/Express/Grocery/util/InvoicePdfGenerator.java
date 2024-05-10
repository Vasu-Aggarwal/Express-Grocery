package com.express.grocery.Express.Grocery.util;

import com.lowagie.text.*;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class InvoicePdfGenerator {
    private static final Logger logger = LoggerFactory.getLogger(InvoicePdfGenerator.class);

    public static ByteArrayInputStream invoiceGenerator(){
        logger.info("PDF is being generated...\n");

        String title = "Order Invoice";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        // Create a PdfPTable with one column
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100); // Set table width to 100% of page width

        // Create a PdfPCell with a height that extends to the bottom of the document
        PdfPCell cell = new PdfPCell();
        cell.setFixedHeight(document.top()-document.bottom()); // Set height to extend to the bottom of the page

        // Add three empty cells to the main cell
        for (int i = 0; i < 3; i++) {
            PdfPCell nestedCell = new PdfPCell();
            nestedCell.setMinimumHeight(20); // Set height for each row
            nestedCell.setBorder(Rectangle.BOTTOM); // Add bottom border for separation
            cell.addElement(nestedCell);
        }

        // Add the cell to the table
        table.addCell(cell);

        // Add the table to the document
        document.add(table);

//        document.add(rectangle);
        document.close();
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

}
