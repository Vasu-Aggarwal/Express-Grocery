package com.express.grocery.Express.Grocery.dto.request;

import com.express.grocery.Express.Grocery.config.AppConstants;
import com.express.grocery.Express.Grocery.entity.Order;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewTransactionRequest {

    @NotNull
    @NotEmpty
    private String userUuid;

    private Integer transactionId;

    @NotNull
    private Double transactionAmount;

    @NotNull
    private Integer transactionStatus;

    @NotNull
    private Integer transactionMode;

    @NotNull
    private Integer order_id;

}
