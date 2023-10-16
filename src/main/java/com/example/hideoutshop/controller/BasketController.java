package com.example.hideoutshop.controller;


import com.example.hideoutshop.config.JwtTokenProvider;
import com.example.hideoutshop.repository.Basket.Basket;
import com.example.hideoutshop.repository.Option.Options;
import com.example.hideoutshop.service.BasketService;
import com.example.hideoutshop.web.dto.CommonDto;
import com.example.hideoutshop.web.dto.ResultDto;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class BasketController {
    private final JwtTokenProvider jwtTokenProvider;
    private final BasketService basketService;
    @ApiOperation("장바구니 담기")
    @GetMapping("/insertBsk")
    public ResponseEntity<ResultDto<Void>> insertBasket(@ModelAttribute Basket basket , @RequestHeader("Authorization") String accessToken){

        String Token = accessToken.replace("Bearer", " ");
        String userid = jwtTokenProvider.getUserId(Token);
        if(userid == null){
            return ResponseEntity.status(403).body(null);
        }else{

            CommonDto commonResponse = basketService.InputBasket(basket,userid);
            ResultDto<Void> result  = ResultDto.in(commonResponse.getStatus(), commonResponse.getMessage());
            return ResponseEntity.status(commonResponse.getHttpStatus()).body(result);
        }

    }


}
