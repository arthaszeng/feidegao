package com.darkhorse.feidegao.infrastructure.repositoryimpl;

import com.darkhorse.feidegao.domainmodel.Order;
import com.darkhorse.feidegao.domainservice.repository.OrderRepository;
import com.darkhorse.feidegao.infrastructure.repositoryimpl.entity.OrderEntity;
import com.darkhorse.feidegao.infrastructure.repositoryimpl.jparepository.OrderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository jpaRepository;

    @Autowired
    public OrderRepositoryImpl(OrderJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Order order) {
        jpaRepository.save(OrderEntity.from(order));
    }

    @Override
    public Order getOrderById(String id) {
        return OrderEntity.to(jpaRepository.getById(id));
    }
}
