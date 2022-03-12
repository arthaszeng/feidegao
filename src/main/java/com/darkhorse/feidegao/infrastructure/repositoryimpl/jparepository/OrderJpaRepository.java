package com.darkhorse.feidegao.infrastructure.repositoryimpl.jparepository;

import com.darkhorse.feidegao.infrastructure.repositoryimpl.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository  extends JpaRepository<OrderEntity, String> {
}
