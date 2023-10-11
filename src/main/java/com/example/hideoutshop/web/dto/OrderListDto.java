package com.example.hideoutshop.web.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderListDto {

    private String ProductNo;
    private Integer Ea;


}
