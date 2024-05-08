package com.express.grocery.Express.Grocery.service;

import com.express.grocery.Express.Grocery.dto.request.OrderCheckoutRequest;
import com.express.grocery.Express.Grocery.dto.request.UpdateOrderStatusRequest;
import com.express.grocery.Express.Grocery.dto.response.OrderCheckoutResponse;

import java.util.Map;
import java.util.Objects;

public interface OrderService {
    OrderCheckoutResponse orderCheckout(OrderCheckoutRequest orderCheckoutRequest);
    Map<String, Object> updateOrderStatus(UpdateOrderStatusRequest updateOrderStatusRequest);
}
