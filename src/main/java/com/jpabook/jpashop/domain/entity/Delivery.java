package com.jpabook.jpashop.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    // ordinal 컬럼이 숫자로 들어감 ordinal 절때 사용 XX
    private DeliveryStatus status; //READY, COMP

}
