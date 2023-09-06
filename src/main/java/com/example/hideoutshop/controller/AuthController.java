package com.example.hideoutshop.controller;

import com.example.hideoutshop.service.AuthService;
import com.example.hideoutshop.web.dto.Login;
import com.example.hideoutshop.web.dto.SignUp;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @ApiOperation("이메일과 패스워드로 회원가입 API")
    @PostMapping(value = "/signup")
    public String register(@RequestBody SignUp SignUpRequest){
        boolean isSuccess = authService.signUp(SignUpRequest);
        return isSuccess ? "회원가입 되었습니다." : "회원가입에 실패하였습니다.";
    }

    @ApiOperation("로그인")
    @PostMapping("/login")
    public String login(@RequestBody Login loginRequest, HttpServletResponse httpServletResponse){
        String token = authService.login(loginRequest);
        httpServletResponse.setHeader("X-AUTH-TOKEN",token);

        return "로그인이 성공하였습니다.";
    }




} // end class
