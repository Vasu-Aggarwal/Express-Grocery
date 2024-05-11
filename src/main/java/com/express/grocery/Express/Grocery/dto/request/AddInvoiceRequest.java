package com.express.grocery.Express.Grocery.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddInvoiceRequest {

    @NotNull(message = "user uuid cannot be null")
    @NotEmpty
    private String userUuid;

    @NotNull(message = "Pass proper invoice status")
    private Integer invoiceStatus;
    private String invoiceUrlPath;

    @NotNull(message = "Shipping Address cannot be null")
    @NotEmpty(message = "Shipping Address cannot be empty")
    private String shippingAddress;

    @NotNull(message = "Billing Address cannot be null")
    @NotEmpty(message = "Billing Address cannot be empty")
    private String billingAddress;

    @NotNull(message = "Contact number cannot be null")
    @Size(min = 10)
    private Long billingContact;

    @NotNull
    private Integer order_Id;

}
