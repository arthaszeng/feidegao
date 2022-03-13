package com.darkhorse.feidegao.infrastructure.repositoryimpl.entity;

import com.darkhorse.feidegao.domainmodel.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static com.darkhorse.feidegao.infrastructure.config.InstantDeserializer.parseToInstant;
import static com.darkhorse.feidegao.infrastructure.config.InstantSerializer.parseToString;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "payments")
public class PaymentEntity {
    @Id
    @GenericGenerator(name = "id_generator", strategy = "uuid")
    @GeneratedValue(generator = "id_generator")
    private String id;

    private String uri;
    private int amount;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "expired_at")
    private String expiredAt;

    public PaymentEntity(String uri, int amount, String createdAt, String expiredAt) {
        this.uri = uri;
        this.amount = amount;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
    }

    public static PaymentEntity from(PaymentRequest paymentRequest) {
        if (paymentRequest == null) {
            return null;
        }

        return new PaymentEntity(paymentRequest.getUri(),
                paymentRequest.getAmount(),
                parseToString(paymentRequest.getCreatedAt()),
                parseToString(paymentRequest.getExpiredAt()));
    }

    public static PaymentRequest to(PaymentEntity paymentEntity) {
        if (paymentEntity == null) {
            return null;
        }
        return new PaymentRequest(paymentEntity.getUri(),
                paymentEntity.getAmount(),
                parseToInstant(paymentEntity.getCreatedAt()),
                parseToInstant(paymentEntity.getExpiredAt()));
    }
}
