package com.example.hideoutshop.service;

import com.example.hideoutshop.repository.Order.Order;
import com.example.hideoutshop.repository.Order.OrderRepository;
import com.example.hideoutshop.web.dto.OrderDto;
import com.example.hideoutshop.web.dto.OrderListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    public void InputOrder(List<OrderDto> orderList, String userid) {
        log.info(orderList.toString());
        Order orderinfo = Order.builder()
                .OrderStateAddress("경기도")
                .OrderStateId(userid)
                .OrderStateTel("010-4540-2969")
                .OrderStateName("이동현")
                .OrderDate(LocalDateTime.now())
                .build();
        orderRepository.save(orderinfo);
        //주문서 생성시 만들어진 주문 번호로 제품 리스트에 저장
        Integer orderStateNo = orderinfo.getOrderStateNo();


    }
}
