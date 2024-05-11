package com.express.grocery.Express.Grocery.dto.request;

import jakarta.validation.constraints.Min;
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
public class OrderCheckoutRequest {

    @NotNull
    @NotEmpty
    private String user;

    @NotNull
    private Integer orderStatus;
}
