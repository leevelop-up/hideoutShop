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
@Table(name="order_list")
public class OrderList{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_list_no")
    private Integer OrderListNo;

    @Column(name = "product_no")
    private Integer ProductNo;
    @Column(name = "product_name")
    private String ProductName;
    @Column(name = "ea")
    private Integer Ea;

    @Column(name = "option_no")
    private Integer OptionNo;
    @Column(name = "reg_date")
    private LocalDateTime localDateTime;

}