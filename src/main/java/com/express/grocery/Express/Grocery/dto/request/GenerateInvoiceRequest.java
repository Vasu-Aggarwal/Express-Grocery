package com.express.grocery.Express.Grocery.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenerateInvoiceRequest {

    @NotNull
    @NotBlank
    private String invoiceNumber;
    @NotNull
    private Integer orderId;

}
