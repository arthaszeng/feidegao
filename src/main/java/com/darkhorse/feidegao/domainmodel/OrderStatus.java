package com.darkhorse.feidegao.domainmodel;

public enum OrderStatus {
    CREATED,
    PAYMENT_REQUESTED;

    public OrderStatus moveToNext() {
        return PAYMENT_REQUESTED;
    }
}
