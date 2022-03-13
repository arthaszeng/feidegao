package com.darkhorse.feidegao.infrastructure.repositoryimpl.entity;

import com.darkhorse.feidegao.domainmodel.AircraftCabin;
import com.darkhorse.feidegao.domainmodel.Contactor;
import com.darkhorse.feidegao.domainmodel.Order;
import com.darkhorse.feidegao.domainmodel.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class OrderEntity {
    @Id
    private String id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "contactor_id")
    private ContactorEntity contactor;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<PassengerEntity> passengers;

    @Column(name = "proposal_id")
    private String proposalId;

    @Column(name = "price")
    private int price;

    @Column(name = "amount")
    private int amount;

    private String status;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "payment_id")
    private PaymentEntity paymentEntity;

    public static OrderEntity from(Order order) {
        return new OrderEntity(
                order.getId(),
                ContactorEntity.form(order.getContactor()),
                order.getPassengers().stream().map(PassengerEntity::from).collect(Collectors.toList()),
                order.getProposalId(),
                order.getPrice(),
                order.getAmount(),
                order.getStatus().toString(),
                order.getCreatedAt(),
                PaymentEntity.from(order.getPaymentRequest())
        );
    }

    public static Order to(OrderEntity orderEntity) {
        return new Order(
                orderEntity.getId(),
                ContactorEntity.to(orderEntity.getContactor()),
                orderEntity.getPassengers().stream().map(PassengerEntity::to).collect(Collectors.toList()),
                orderEntity.getProposalId(),
                orderEntity.getPrice(),
                orderEntity.getAmount(),
                OrderStatus.valueOf(orderEntity.getStatus()),
                orderEntity.getCreatedAt(),
                PaymentEntity.to(orderEntity.getPaymentEntity())
        );
    }
}
