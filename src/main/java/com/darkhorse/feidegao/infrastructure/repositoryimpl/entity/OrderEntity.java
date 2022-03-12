package com.darkhorse.feidegao.infrastructure.repositoryimpl.entity;

import com.darkhorse.feidegao.domainmodel.Order;
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

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    public static OrderEntity from(Order order) {
        return new OrderEntity(
                order.getId(),
                ContactorEntity.form(order.getContactor()),
                order.getPassengers().stream().map(PassengerEntity::from).collect(Collectors.toList()),
                order.getProposalId(),
                order.getPrice(),
                order.getAmount(),
                order.getCreatedAt()
        );
    }
}
