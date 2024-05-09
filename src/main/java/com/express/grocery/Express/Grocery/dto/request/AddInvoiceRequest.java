package com.express.grocery.Express.Grocery.dto.request;

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
public class AddInvoiceRequest {

    @NotNull
    private Integer invoiceStatus;
    private String invoiceUrlPath;

    @NotNull
    @NotEmpty
    private String shippingAddress;

    @NotNull
    @NotEmpty
    private String billingAddress;

    @NotNull
    @NotEmpty
    private Integer billingContact;

    @NotNull
    private Integer order_Id;

}
