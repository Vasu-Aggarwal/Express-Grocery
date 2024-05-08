package com.express.grocery.Express.Grocery.config;

import lombok.Getter;

@Getter
public enum AppConstants {

    INACTIVE_COUPON(0),
    ACTIVE_COUPON(1),
    DELETED_COUPON(2),
    EXPIRED_COUPON(3),
    ORDER_CREATED(0),
    ORDER_PENDING(3),
    ORDER_IN_TRANSACT(2),
    ORDER_PLACED(1),
    ORDER_CANCELLED(4);


    private final int value;

    AppConstants(int value) {
        this.value = value;
    }
}
