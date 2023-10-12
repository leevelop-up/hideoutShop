package com.example.hideoutshop.repository.Basket;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private String UserId;

    @Column(name="product_no")
    private Integer productNo;

    @Column(name="option_no")
    private Integer optionNo;

    @Column(name="ea")
    private Integer Ea;

}
