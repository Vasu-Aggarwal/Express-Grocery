package com.express.grocery.Express.Grocery.service;

import com.express.grocery.Express.Grocery.dto.request.NewTransactionRequest;
import com.express.grocery.Express.Grocery.dto.response.NewTransactionResponse;

public interface TransactionService {

    NewTransactionResponse newTransaction(NewTransactionRequest newTransactionRequest);

}
