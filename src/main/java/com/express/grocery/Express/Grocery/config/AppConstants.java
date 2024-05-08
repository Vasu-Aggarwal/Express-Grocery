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
    ORDER_CANCELLED(4),

    TRANSACTION_SUCCESSFUL(1),
    TRANSACTION_FAIL(2),
    TRANSACTION_PENDING(0),
    TRANSACTION_CANCELLED(4),

    TRANSACTION_MODE_COD(0),
    TRANSACTION_MODE_UPI(1),
    TRANSACTION_MODE_CREDIT_CARD(2),
    TRANSACTION_MODE_DEBIT_CARD(3),
    TRANSACTION_MODE_NET_BANKING(4);


    private final int value;

    AppConstants(int value) {
        this.value = value;
    }
}
