package com.express.grocery.Express.Grocery.controller;

import com.express.grocery.Express.Grocery.dto.request.NewTransactionRequest;
import com.express.grocery.Express.Grocery.dto.response.NewTransactionResponse;
import com.express.grocery.Express.Grocery.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/newTransact")
    public ResponseEntity<NewTransactionResponse> newTransaction(@RequestBody @Valid NewTransactionRequest newTransactionRequest){
        NewTransactionResponse newTransactionResponse = transactionService.newTransaction(newTransactionRequest);
        return new ResponseEntity<>(newTransactionResponse, HttpStatus.OK);
    }

}
