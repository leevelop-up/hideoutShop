package com.example.hideoutshop.web.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Integer orderStateNo;
    private String orderStateId;
    private List<OrderListDto> orderList;
}
