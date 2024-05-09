package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.config.AppConstants;
import com.express.grocery.Express.Grocery.dto.request.AddInvoiceRequest;
import com.express.grocery.Express.Grocery.entity.Invoice;
import com.express.grocery.Express.Grocery.entity.Order;
import com.express.grocery.Express.Grocery.entity.Transaction;
import com.express.grocery.Express.Grocery.exception.ResourceNotFoundException;
import com.express.grocery.Express.Grocery.repository.InvoiceRepository;
import com.express.grocery.Express.Grocery.repository.OrderRepository;
import com.express.grocery.Express.Grocery.repository.TransactionRepository;
import com.express.grocery.Express.Grocery.service.InvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Map<String, Object> addInvoice(AddInvoiceRequest addInvoiceRequest) {

        //Find order
        Order order = orderRepository.findById(addInvoiceRequest.getOrder_Id()).orElseThrow(() -> new ResourceNotFoundException("Order not found", 0));

        //Get the transaction of that order
        Transaction transaction = order.getTransaction();

        Map<String, Object> response = new HashMap<>();

        if (transaction.getTransactionStatus() == AppConstants.TRANSACTION_SUCCESSFUL.getValue()){

            Invoice invoice = modelMapper.map(addInvoiceRequest, Invoice.class);

        } else {
            response.put("message: ", String.format("Your transaction status is %s", transaction.getTransactionStatus()));
            response.put("status: ", 0);
        }
        return response;
    }
}
