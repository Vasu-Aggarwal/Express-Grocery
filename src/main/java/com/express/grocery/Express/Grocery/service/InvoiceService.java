package com.express.grocery.Express.Grocery.service;

import com.express.grocery.Express.Grocery.dto.request.AddInvoiceRequest;
import com.express.grocery.Express.Grocery.dto.request.GenerateInvoiceRequest;
import com.express.grocery.Express.Grocery.dto.response.GenerateInvoiceResponse;

import java.util.Map;

public interface InvoiceService {

    Map<String, Object> addInvoice(AddInvoiceRequest addInvoiceRequest);
    GenerateInvoiceResponse generateInvoice(GenerateInvoiceRequest generateInvoiceRequest);
}
