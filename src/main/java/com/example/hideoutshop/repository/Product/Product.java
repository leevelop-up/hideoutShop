package com.example.hideoutshop.repository.Product;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "product")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pd_no")
    private Integer productNo;

    @Column(name = "pd_name")
    private String productName;

    @Column(name = "pd_content")
    private String productContent;
    @Column(name = "pd_bigdiv")
    private Integer productBigDivNo;

    @Column(name = "pd_mediumdiv")
    private Integer productMediumDivNo;

    @Column(name = "pd_smalldiv")
    private Integer productSmallDivNo;

    @Column(name = "pd_price")
    private Integer productPrice;

    @Column(name = "pd_option")
    private Character productOptionShow;

    @Column(name = "pd_img")
    private String productImg;

    @Column(name = "pd_path")
    private String productPath;

    @Column(name = "pd_writer")
    private String productWriter;

}
