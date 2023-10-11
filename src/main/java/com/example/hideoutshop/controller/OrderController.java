package com.example.hideoutshop.controller;


import com.example.hideoutshop.config.JwtTokenProvider;
import com.example.hideoutshop.repository.Option.Options;
import com.example.hideoutshop.service.OrderService;
import com.example.hideoutshop.web.dto.OrderDto;
import com.example.hideoutshop.web.dto.OrderListDto;
import com.example.hideoutshop.web.dto.ProductDTO;
import com.example.hideoutshop.web.dto.ResultDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;
    private final JwtTokenProvider jwtTokenProvider;
    @ApiOperation("상품 구매")
    @PostMapping(value = "order", consumes = "application/json")
    public ResponseEntity<ResultDto<Void>> Order(@RequestBody List<OrderDto> orderList, @RequestHeader("Authorization") String accessToken){
        ArrayList<OrderDto> list = (ArrayList<OrderDto>)orderList;
        //주문시 주문서 생성(OrderState,Orderlist)
        System.out.println(orderList);
        System.out.println("aaa");

        //결제 실패시 주문서 삭제
        String Token = accessToken.replace("Bearer", " ");
        String userid = jwtTokenProvider.getUserId(Token);

        if(userid == null){
            return ResponseEntity.status(403).body(null);
        }else{

            orderService.InputOrder(orderList,userid);
            return ResponseEntity.status(403).body(null);
        }
    }
}
