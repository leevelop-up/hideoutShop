package com.example.hideoutshop.web.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer productNo;
    private String productName;
    private String productContent;
    private Integer productBigDivNo;
    private Integer productMediumDivNo;
    private Integer productSmallDivNo;
    private Integer productPrice;
    private Character productOptionShow;
    private String productImg;
    private String productPath;
    private String productWriter;


}