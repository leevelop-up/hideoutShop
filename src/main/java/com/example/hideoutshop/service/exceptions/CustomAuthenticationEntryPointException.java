package com.example.hideoutshop.service.exceptions;

public class CustomAuthenticationEntryPointException extends RuntimeException{
    public CustomAuthenticationEntryPointException(String msg) {
        super(msg);
    }
}
