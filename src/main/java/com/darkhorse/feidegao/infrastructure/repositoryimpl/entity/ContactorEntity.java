package com.darkhorse.feidegao.infrastructure.repositoryimpl.entity;

import com.darkhorse.feidegao.domainmodel.Contactor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "contactors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ContactorEntity {
    @Id
    @GenericGenerator(name = "id_generator", strategy = "uuid")
    @GeneratedValue(generator = "id_generator")
    private String id;

    @Column(name = "phone_number")
    private String phoneNumber;

    public static ContactorEntity form(Contactor contactor) {
        return new ContactorEntity(contactor.getId(), contactor.getPhoneNumber());
    }

    public static Contactor to(ContactorEntity contactorEntity) {
        return new Contactor(contactorEntity.getId(), contactorEntity.getPhoneNumber());
    }
}
