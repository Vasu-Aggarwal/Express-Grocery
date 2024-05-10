package com.express.grocery.Express.Grocery.controller;

import com.express.grocery.Express.Grocery.dto.request.AddInvoiceRequest;
import com.express.grocery.Express.Grocery.service.InvoiceService;
import com.express.grocery.Express.Grocery.util.InvoicePdfGenerator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/addInvoice")
    public ResponseEntity<Map<String, Object>> addInvoice(@RequestBody @Valid AddInvoiceRequest addInvoiceRequest){
        Map<String, Object> response = invoiceService.addInvoice(addInvoiceRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/generateInvoice")
    public ResponseEntity<InputStreamResource> generateInvoice(){
        ByteArrayInputStream pdf = InvoicePdfGenerator.invoiceGenerator();
        HttpHeaders httpHeaders = new HttpHeaders();
        String filename = String.valueOf(Instant.now().toEpochMilli());
        httpHeaders.add("Content-Disposition", "inline;file="+filename+".pdf");

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }

}
