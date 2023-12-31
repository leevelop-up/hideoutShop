package com.example.hideoutshop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HtmlController {
    @ApiOperation("테스트메인페이지")
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @ApiOperation("test")
    @GetMapping("/login")
    public String member(){
        return "login";
    }

    @ApiOperation("로그인")
    @GetMapping("/signin")
    public String sign(){
        return "signin";
    }

    @ApiOperation("상품 입려")
    @GetMapping("/setting")
    public String setting(){
        return "setting";
    }

    @ApiOperation("상품 입려")
    @GetMapping("/detail")
    public String detail(){
        return "detail";
    }

    @ApiOperation("내 상품 수정")
    @GetMapping("/mylist")
    public String mylist(){
        return "mylist";
    }

    @ApiOperation("내 상품 구매")
    @GetMapping("/order")
    public String order(){
        return "order";
    }

}
