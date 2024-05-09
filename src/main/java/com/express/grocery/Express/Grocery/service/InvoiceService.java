package com.express.grocery.Express.Grocery.service;

import com.express.grocery.Express.Grocery.dto.request.AddInvoiceRequest;

import java.util.Map;

public interface InvoiceService {

    Map<String, Object> addInvoice(AddInvoiceRequest addInvoiceRequest);

}
