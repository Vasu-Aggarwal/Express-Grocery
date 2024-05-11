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
public class RemoveFromCart {

    @NotNull
    private Integer cartDetailId;

    @NotNull
    @NotEmpty
    private String userUuid;

    @NotNull
    private Integer product;

    @NotNull
    private Integer productQuantity;

}
