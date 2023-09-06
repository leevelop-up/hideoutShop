package com.example.hideoutshop.repository.list;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "product")
public class Product {

    @Id @Column(name = "product_name")
    private String productName;

    @Column(name = "product_content")
    private String productContent;

    @Column(name = "product_img")
    private String productImg;

    @Column(name = "product_path")
    private String productPath;
}
