package com.example.hideoutshop.controller;


import com.example.hideoutshop.config.JwtTokenProvider;
import com.example.hideoutshop.repository.Option.Options;
import com.example.hideoutshop.service.OrderService;
import com.example.hideoutshop.web.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
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
    public ResponseEntity<ResultDto<Void>> Order(@RequestBody OrderDto orderList, @RequestHeader("Authorization") String accessToken) throws JsonProcessingException, JSONException {

        //결제 실패시 주문서 삭제
        String Token = accessToken.replace("Bearer", " ");
        String userid = jwtTokenProvider.getUserId(Token);

        if(userid == null){
            return ResponseEntity.status(403).body(null);
        }else{

            CommonDto updatedCommonResponse = orderService.InputOrder(orderList,userid);
            ResultDto<Void> result  = ResultDto.in(updatedCommonResponse.getStatus(), updatedCommonResponse.getMessage());
            return ResponseEntity.status(updatedCommonResponse.getHttpStatus()).body(result);
        }
    }
}
