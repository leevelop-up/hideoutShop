package com.example.hideoutshop.service;

import com.example.hideoutshop.web.dto.CommonDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ErrorService {
    public CommonDto createSuccessResponse(String message, HttpStatus httpStatus, Object data) {
        return CommonDto.builder()
                .message(message)
                .status("success")
                .httpStatus(httpStatus)
                .data(data)
                .build();
    }
    public CommonDto createErrorResponse(String message, HttpStatus httpStatus, Object data) {
        return CommonDto.builder()
                .message(message)
                .status("fail")
                .httpStatus(httpStatus)
                .data(data)
                .build();
    }
}