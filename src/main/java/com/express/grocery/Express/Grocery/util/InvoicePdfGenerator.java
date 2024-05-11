package com.express.grocery.Express.Grocery.util;

import com.express.grocery.Express.Grocery.dto.response.AddToCartResponse;
import com.express.grocery.Express.Grocery.entity.CartDetail;
import com.express.grocery.Express.Grocery.entity.Invoice;
import com.express.grocery.Express.Grocery.entity.InvoiceParticular;
import com.express.grocery.Express.Grocery.entity.Product;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class InvoicePdfGenerator {
    private static final Logger logger = LoggerFactory.getLogger(InvoicePdfGenerator.class);

    private static final String[] units = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public static ByteArrayInputStream invoiceGenerator(Invoice invoice, InvoiceParticular invoiceParticular, List<CartDetail> cartDetails) {
        logger.info("PDF is being generated...\n");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        //Default font
        Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);

        //Main table
        PdfPTable table = new PdfPTable(14);
        table.setWidthPercentage(100);

        //Table heading
        PdfPCell heading = new PdfPCell(new Paragraph("TAX INVOICE", headerFont));
        heading.setGrayFill(0.8f);
        heading.setPadding(5);
        heading.setFixedHeight(20);
        heading.setHorizontalAlignment(Element.ALIGN_CENTER);
        heading.setVerticalAlignment(Element.ALIGN_MIDDLE);
        heading.setColspan(14);
        table.addCell(heading);

        //Company name and address details
        Phrase company = new Phrase();
        company.setLeading(10);
        company.add(new Chunk("Express Grocery Pvt. Ltd.\nBhola nath nagar, Shahdara,\nDelhi - 110032", textFont));
        PdfPCell companyDetails = new PdfPCell();
        companyDetails.setColspan(14);
        companyDetails.setPadding(5);
        companyDetails.addElement(company);

        //Company GST Details and Order details
        Phrase gstin = new Phrase();
        gstin.setLeading(10);
        gstin.add(new Chunk("GSTIN: ", headerFont));
        gstin.add(new Chunk("09QVAFS9862Q1Z8", textFont));
        gstin.add(new Chunk("\nPAN: ", headerFont));
        gstin.add(new Chunk("DGUPA0170", textFont));
        gstin.add(new Chunk("\nInvoice Number: ", headerFont));
        gstin.add(new Chunk(invoice.getInvoiceNumber(), textFont));
        gstin.add(new Chunk("\nInvoice Date: ", headerFont));
        gstin.add(new Chunk(invoice.getInvoiceDate().toString(), textFont));
        PdfPCell companyFinancialDetails = new PdfPCell();
        companyFinancialDetails.setPadding(5);
        companyFinancialDetails.addElement(gstin);
        companyFinancialDetails.setColspan(7);

        Phrase order = new Phrase();
        order.setLeading(10);
        order.add(new Chunk("Order ID: ", headerFont));
        order.add(new Chunk(invoice.getOrder_Id().getOrderId().toString(), textFont));
        order.add(new Chunk("\nMode of Payment: ", headerFont));
        order.add(new Chunk(invoice.getTransaction_id().getTransactionMode().toString(), textFont));
        PdfPCell companyOrder = new PdfPCell();
        companyOrder.addElement(order);
        companyOrder.setColspan(7);
        companyOrder.setPadding(5);

        table.addCell(companyDetails);
        table.addCell(companyFinancialDetails);
        table.addCell(companyOrder);

        //consignee header
        PdfPCell receiver = new PdfPCell(new Paragraph("Details of Receiver (Billed to)", headerFont));
        PdfPCell consignee = new PdfPCell(new Paragraph("Details of Consignee (Shipped to)", headerFont));
        receiver.setColspan(7);
        receiver.setPadding(5);
        consignee.setColspan(7);
        consignee.setPadding(5);
        receiver.setGrayFill(0.8f);
        consignee.setGrayFill(0.8f);
        receiver.setHorizontalAlignment(Element.ALIGN_CENTER);
        receiver.setVerticalAlignment(Element.ALIGN_MIDDLE);
        consignee.setHorizontalAlignment(Element.ALIGN_CENTER);
        consignee.setVerticalAlignment(Element.ALIGN_MIDDLE);

        table.addCell(receiver);
        table.addCell(consignee);

        //Customer Details
        Phrase receiverDetails = new Phrase();
        receiverDetails.setLeading(10);
        receiverDetails.add(new Chunk("Name: ", headerFont));
        receiverDetails.add(new Chunk(invoice.getOrder_Id().getUser().getName(), textFont));
        receiverDetails.add(new Chunk("\nAddress: ", headerFont));
        receiverDetails.add(new Chunk(invoice.getBillingAddress(), textFont));
        receiverDetails.add(new Chunk("\nEmail: ", headerFont));
        receiverDetails.add(new Chunk(invoice.getOrder_Id().getUser().getEmail(), textFont));
        receiverDetails.add(new Chunk("\nContact: ", headerFont));
        receiverDetails.add(new Chunk(invoice.getBillingContact().toString(), textFont));

        Phrase consigneeDetails = new Phrase();
        consigneeDetails.setLeading(10);
        consigneeDetails.add(new Chunk("Name: ", headerFont));
        consigneeDetails.add(new Chunk(invoice.getOrder_Id().getUser().getName(), textFont));
        consigneeDetails.add(new Chunk("\nAddress: ", headerFont));
        consigneeDetails.add(new Chunk(invoice.getShippingAddress(), textFont));
        consigneeDetails.add(new Chunk("\nEmail: ", headerFont));
        consigneeDetails.add(new Chunk(invoice.getOrder_Id().getUser().getEmail(), textFont));
        consigneeDetails.add(new Chunk("\nContact: ", headerFont));
        consigneeDetails.add(new Chunk(invoice.getBillingContact().toString(), textFont));

        PdfPCell receiverCol = new PdfPCell();
        receiverCol.addElement(receiverDetails);
        receiverCol.setPadding(5);
        PdfPCell consigneeCol = new PdfPCell();
        consigneeCol.addElement(consigneeDetails);
        consigneeCol.setPadding(5);
        receiverCol.setColspan(7);
        consigneeCol.setColspan(7);
        table.addCell(receiverCol);
        table.addCell(consigneeCol);

        //Product details

        PdfPCell[] headersRow = {
                new PdfPCell(new Paragraph("S.No", headerFont)),
                new PdfPCell(new Paragraph("Product Id", headerFont)),
                new PdfPCell(new Paragraph("Particulars", headerFont)),
                new PdfPCell(new Paragraph("Qty", headerFont)),
                new PdfPCell(new Paragraph("Unit Price", headerFont)),
                new PdfPCell(new Paragraph("Discount", headerFont)),
                new PdfPCell(new Paragraph("Tax", headerFont)),
        };

        PdfPCell[] headersCOl = {
                new PdfPCell(new Paragraph("CGST", headerFont)),
                new PdfPCell(new Paragraph("SGST", headerFont)),
                new PdfPCell(new Paragraph("IGST", headerFont)),
        };

        PdfPCell[] headAfter = {
                new PdfPCell(new Paragraph("Rate %", headerFont)),
                new PdfPCell(new Paragraph("Amount", headerFont)),
                new PdfPCell(new Paragraph("Rate %", headerFont)),
                new PdfPCell(new Paragraph("Amount", headerFont)),
                new PdfPCell(new Paragraph("Rate %", headerFont)),
                new PdfPCell(new Paragraph("Amount", headerFont))
        };

        for (PdfPCell header : headersRow) {
            header.setRowspan(2);
            header.setPadding(5);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(header);
        }

        for (PdfPCell header : headersCOl) {
            header.setColspan(2);
            header.setPadding(5);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(header);
        }

        PdfPCell productHeaderTotalAmount = new PdfPCell(new Paragraph("Total Amount", headerFont));
        productHeaderTotalAmount.setColspan(2);
        productHeaderTotalAmount.setRowspan(2);
        productHeaderTotalAmount.setPadding(5);
        table.addCell(productHeaderTotalAmount);

        for (PdfPCell header : headAfter) {
            header.setPadding(5);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(header);
        }

        int sno = 1;
        double netAmount = 0.00;

        for (CartDetail cartDetail: cartDetails){
            ProductCalculatorHelper productCalculatorHelper = new ProductCalculatorHelper();
            productCalculatorHelper.findProductDiscount(cartDetail.getProduct());

            table.addCell(createCell(Integer.toString(sno), textFont));
            table.addCell(createCell(cartDetail.getProduct().getProductId().toString(), textFont));
            table.addCell(createCell(cartDetail.getProduct().getProductName(), textFont));
            table.addCell(createCell(cartDetail.getProductQuantity().toString(), textFont));
            table.addCell(createCell(cartDetail.getProduct().getProductPrice().toString(), textFont));
            table.addCell(createCell(productCalculatorHelper.getProductDiscountedAmount().toString(), textFont));
            table.addCell(createCell(String.valueOf(invoiceParticular.getGstRate()*productCalculatorHelper.getProductDiscountedAmount()), textFont));
            table.addCell(createCell("-", textFont));
            table.addCell(createCell("-", textFont));
            table.addCell(createCell("-", textFont));
            table.addCell(createCell("-", textFont));
            table.addCell(createCell("-", textFont));
            table.addCell(createCell("-", textFont));
            table.addCell(createCell(String.valueOf((productCalculatorHelper.getProductDiscountedAmount()*cartDetail.getProductQuantity())+invoiceParticular.getGstRate()*productCalculatorHelper.getProductDiscountedAmount()), textFont));
            netAmount += (productCalculatorHelper.getProductDiscountedAmount()*cartDetail.getProductQuantity())+invoiceParticular.getGstRate()*productCalculatorHelper.getProductDiscountedAmount();
            sno++;
        }

        //Net invoice
        Phrase netInvoice = new Phrase();
        netInvoice.add(new Chunk("Net Invoice Value", headerFont));
        PdfPCell netInvoiceCol = new PdfPCell();
        netInvoiceCol.addElement(netInvoice);
        netInvoiceCol.setPadding(5);
        netInvoiceCol.setColspan(3);
        PdfPCell space = new PdfPCell(new Paragraph(""));
        space.setPadding(5);
        space.setColspan(10);
        PdfPCell netInvoiceAmountCol = new PdfPCell(new Paragraph(String.valueOf(netAmount), textFont));
        netInvoiceAmountCol.setPadding(5);

        table.addCell(netInvoiceCol);
        table.addCell(space);
        table.addCell(netInvoiceAmountCol);

        //Invoice in words
        Phrase invoicePhrase = new Phrase();
        invoicePhrase.add(new Chunk("Invoice Value (In words): ", headerFont));
        invoicePhrase.add(new Chunk(convertNumberToWords((int) netAmount)+" Only", textFont));
        PdfPCell invoiceCol = new PdfPCell();
        invoiceCol.setPadding(5);
        invoiceCol.addElement(invoicePhrase);
        invoiceCol.setColspan(14);
        table.addCell(invoiceCol);

        PdfPCell registeredOffice = new PdfPCell(new Paragraph("Registered Office: Bhola nath nagar, Shahdara, Delhi - 110032", FontFactory.getFont(FontFactory.HELVETICA, 8)));
        registeredOffice.setPadding(5);
        registeredOffice.setHorizontalAlignment(Element.ALIGN_CENTER);
        registeredOffice.setColspan(14);
        table.addCell(registeredOffice);

        document.add(table);
        document.close();
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    public static PdfPCell createCell(String text, Font font) {
        Phrase phrase = new Phrase(text, font);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setPadding(5);
        cell.setFixedHeight(70);
        return cell;
    }

    public static String convertNumberToWords(int number) {
        if (number == 0) {
            return "Zero";
        }
        return convert(number);
    }

    private static String convert(int number) {
        if (number < 0) {
            return "Minus " + convert(-number);
        }
        if (number < 10) {
            return units[number];
        }
        if (number < 20) {
            return teens[number - 10];
        }
        if (number < 100) {
            return tens[number / 10] + ((number % 10 != 0) ? " " + convert(number % 10) : "");
        }
        if (number < 1000) {
            return units[number / 100] + " Hundred" + ((number % 100 != 0) ? " " + convert(number % 100) : "");
        }
        if (number < 100000) {
            return convert(number / 1000) + " Thousand" + ((number % 1000 != 0) ? " " + convert(number % 1000) : "");
        }
        if (number < 10000000) {
            return convert(number / 100000) + " Lakh" + ((number % 100000 != 0) ? " " + convert(number % 100000) : "");
        }
        return convert(number / 10000000) + " Crore" + ((number % 10000000 != 0) ? " " + convert(number % 10000000) : "");
    }

}
