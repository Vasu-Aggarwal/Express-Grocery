package com.express.grocery.Express.Grocery.service;

import com.express.grocery.Express.Grocery.dto.request.AddInvoiceRequest;
import org.springframework.core.io.InputStreamResource;

import java.io.ByteArrayInputStream;
import java.util.Map;

public interface InvoiceService {

    Map<String, Object> addInvoice(AddInvoiceRequest addInvoiceRequest);
    ByteArrayInputStream generateInvoice(String invoiceNumber);
}
