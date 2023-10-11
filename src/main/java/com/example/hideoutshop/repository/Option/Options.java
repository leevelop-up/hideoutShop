package com.example.hideoutshop.repository.Option;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "options")
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_no")
    private Integer OptionNo;

    @Column(name = "product_no")
    private Integer ProductNo;

    @Column(name="option_name")
    private String OptionName;

    @Column(name="product_price")
    private Integer OptionPrice;

}
