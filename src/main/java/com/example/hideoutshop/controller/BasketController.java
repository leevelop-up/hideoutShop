package com.example.hideoutshop.controller;


import com.example.hideoutshop.config.JwtTokenProvider;
import com.example.hideoutshop.web.dto.CommonDto;
import com.example.hideoutshop.web.dto.ResultDto;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class BasketController {
    private final JwtTokenProvider jwtTokenProvider;
    @ApiOperation("장바구니 담기")
    @GetMapping("/insertBsk")
    public ResponseEntity<ResultDto<Void>> insertBasket(@RequestHeader("Authorization") String accessToken){

        String Token = accessToken.replace("Bearer", " ");
        String userid = jwtTokenProvider.getUserId(Token);
        if(userid == null){
            return ResponseEntity.status(403).body(null);
        }else{

            //CommonDto updatedCommonResponse = .InputOrder(orderList,userid);
//            ResultDto<Void> result  = ResultDto.in(updatedCommonResponse.getStatus(), updatedCommonResponse.getMessage());
//            return ResponseEntity.status(updatedCommonResponse.getHttpStatus()).body(result);
        }

    }


}
