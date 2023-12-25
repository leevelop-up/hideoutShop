package com.example.hideoutshop.service;

import com.example.hideoutshop.repository.Order.Order;
import com.example.hideoutshop.repository.Order.OrderList;
import com.example.hideoutshop.repository.Order.OrderListRepository;
import com.example.hideoutshop.repository.Order.OrderRepository;
import com.example.hideoutshop.web.dto.CommonDto;
import com.example.hideoutshop.web.dto.OrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderListRepository orderListRepository;
    private final ErrorService errorService;
    public CommonDto InputOrder(OrderDto orderList, String userid) throws JsonProcessingException, JSONException {
        int productNo, ea = 0;
        ObjectMapper objectMapper = new ObjectMapper();
        String orderDtoJson = objectMapper.writeValueAsString(orderList);

        JSONObject jsonObject = new JSONObject(orderDtoJson);

        // JSON 객체에서 원하는 키와 값 추출
        //int orderStateNo1 = jsonObject.getInt("orderStateNo");
        JSONArray orderListArray = jsonObject.getJSONArray("orderList");

        Order orderinfo = Order.builder()
                .OrderStateAddress("경기도")
                .OrderStateId(userid)
                .OrderStateTel("010-4540-****")
                .OrderStateName("이동현")
                .OrderDate(LocalDateTime.now())
                .build();
        orderRepository.save(orderinfo);

        //주문서 생성시 만들어진 주문 번호로 제품 리스트에 저장
        Integer orderStateNo = orderinfo.getOrderStateNo();
        for (int i = 0; i < orderListArray.length(); i++) {
            JSONObject orderItem = orderListArray.getJSONObject(i);
            productNo = orderItem.getInt("productno");
            ea = orderItem.getInt("ea");

            OrderList orderListInfo = OrderList.builder()
                    .OrderStateNo(orderStateNo)
                    .ProductNo(productNo)
                    .ProductName("제품등록")
                    .Ea(ea)
                    .OptionNo(1)
                    .orderDate(LocalDateTime.now())
                    .build();
            orderListRepository.save(orderListInfo);
        }


        return errorService.createSuccessResponse("구매 완료", HttpStatus.OK,null);

    }
}
