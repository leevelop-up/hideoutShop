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


}
