package com.darkhorse.feidegao.infrastructure.repositoryimpl.entity;

import com.darkhorse.feidegao.domainmodel.Passenger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "passengers")
public class PassengerEntity {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "id_number")
    private String idNumber;

    public static PassengerEntity from(Passenger passenger) {
        return new PassengerEntity(passenger.getId(), passenger.getName(), passenger.getIdNumber());
    }

    public static Passenger to(PassengerEntity passengerEntity) {
        return new Passenger(passengerEntity.getId(), passengerEntity.getName(), passengerEntity.getIdNumber());
    }
}
