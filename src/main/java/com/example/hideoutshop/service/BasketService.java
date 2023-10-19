package com.example.hideoutshop.service;

import com.example.hideoutshop.repository.Basket.Basket;
import com.example.hideoutshop.repository.Basket.BasketRepository;
import com.example.hideoutshop.web.dto.CommonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;
    private final ErrorService errorService;

    public CommonDto InputBasket(Basket basket, String userid) {
        Integer Option = 0;
        if(basket.getOptionNo() == null){

        }else{
            Option = basket.getOptionNo();
        }


        Basket basketInfo = Basket.builder()
                .UserId(userid)
                .productNo(basket.getProductNo())
                .Ea(basket.getEa())
                .optionNo(Option)
                .build();

        basketRepository.save(basketInfo);
        return errorService.createSuccessResponse("장바구니 담기 완료", HttpStatus.OK,null);
    }
}
