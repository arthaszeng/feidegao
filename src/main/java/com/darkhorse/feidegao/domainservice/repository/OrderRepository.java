package com.darkhorse.feidegao.domainservice.repository;

import com.darkhorse.feidegao.domainmodel.Order;

public interface OrderRepository {
    void save(Order order);
}