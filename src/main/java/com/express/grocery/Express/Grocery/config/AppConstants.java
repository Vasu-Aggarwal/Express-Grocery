package com.express.grocery.Express.Grocery.config;

import lombok.Getter;

@Getter
public enum AppConstants {

    INACTIVE_COUPON(0),
    ACTIVE_COUPON(1),
    DELETED_COUPON(2),
    EXPIRED_COUPON(3);


    private final int value;

    AppConstants(int value) {
        this.value = value;
    }
}
