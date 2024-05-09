package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.config.AppConstants;
import com.express.grocery.Express.Grocery.dto.request.NewTransactionRequest;
import com.express.grocery.Express.Grocery.dto.response.NewTransactionResponse;
import com.express.grocery.Express.Grocery.entity.Order;
import com.express.grocery.Express.Grocery.entity.Transaction;
import com.express.grocery.Express.Grocery.exception.BadRequestException;
import com.express.grocery.Express.Grocery.exception.ResourceNotFoundException;
import com.express.grocery.Express.Grocery.repository.OrderRepository;
import com.express.grocery.Express.Grocery.repository.TransactionRepository;
import com.express.grocery.Express.Grocery.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public NewTransactionResponse newTransaction(NewTransactionRequest newTransactionRequest) {
        Order order = orderRepository.findById(newTransactionRequest.getOrder_id()).orElseThrow(() -> new ResourceNotFoundException("Order not found", 0));


        if (order.getOrderStatus() == AppConstants.ORDER_IN_TRANSACT.getValue()){
            if (newTransactionRequest.getTransactionId() == null){
                throw new BadRequestException("Your order is in transaction, please pass proper transaction details");
            }
        }

        Transaction transaction = modelMapper.map(newTransactionRequest, Transaction.class);
        if (order.getOrderStatus() == AppConstants.ORDER_IN_TRANSACT.getValue()){
            transaction.setTransactionId(newTransactionRequest.getTransactionId());
        }
        transaction.setTransactionMode(newTransactionRequest.getTransactionMode());
        transaction.setTransactionStatus(newTransactionRequest.getTransactionStatus());
        transaction.setOrder_id(order);

        return modelMapper.map(transactionRepository.save(transaction), NewTransactionResponse.class);
    }
}
