package com.express.grocery.Express.Grocery.dto;

import com.express.grocery.Express.Grocery.entity.InvoiceParticular;
import com.express.grocery.Express.Grocery.entity.Order;
import com.express.grocery.Express.Grocery.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {

    private Integer invoiceId;
    private Integer invoiceStatus;
    private String invoiceNumber;
    private String invoiceUrlPath;
    private String shippingAddress;
    private String billingAddress;
    private Long billingContact;
    private LocalDateTime invoiceDate;
    private Order order_Id;
    private Transaction transaction_id;
    private InvoiceParticular inp;

}
