package com.express.grocery.Express.Grocery.dto.response;

import com.express.grocery.Express.Grocery.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewTransactionResponse {

    private Integer transactionId;
    private Double transactionAmount;
    private Integer transactionStatus;
    private Integer transactionMode;
    private Order order_id;
    private LocalDateTime addedOn;

}
