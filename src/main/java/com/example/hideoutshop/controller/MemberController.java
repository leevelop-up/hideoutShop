package com.example.hideoutshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {


    @GetMapping("/login1/{id}")
    public String Login(@PathVariable Integer id){

        if(id == 1){
            return "맞아요";
        }else{
            return "안되요";
        }

    }

}
