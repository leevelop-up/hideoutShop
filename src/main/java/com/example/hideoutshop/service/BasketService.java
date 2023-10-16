package com.example.hideoutshop.service;

import com.example.hideoutshop.repository.Basket.Basket;
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
    private final ErrorService errorService;

    public CommonDto InputBasket(Basket basket, String userid) {



        return errorService.createSuccessResponse("구매 완료", HttpStatus.OK,null);
    }
}
