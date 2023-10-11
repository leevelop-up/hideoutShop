package com.example.hideoutshop.repository.Order;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderstate")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderstate_no")
    private Integer OrderStateNo;
    @Column(name = "orderstate_id")
    private String OrderStateId;
    @Column(name = "orderstate_tel")
    private String OrderStateTel;
    @Column(name = "orderstate_name")
    private String OrderStateName;
    @Column(name = "orderstate_address")
    private String OrderStateAddress;
    @Column(name = "orderstate_date")
    private LocalDateTime OrderDate;


}



