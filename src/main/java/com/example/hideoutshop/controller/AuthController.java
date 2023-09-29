package com.example.hideoutshop.controller;

import com.example.hideoutshop.repository.member.Member;
import com.example.hideoutshop.service.AuthService;
import com.example.hideoutshop.web.dto.Response;
import com.example.hideoutshop.web.dto.SignUp;
import com.example.hideoutshop.web.dto.UserRequestDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final Response response;
    @ApiOperation("이메일과 패스워드로 회원가입 API")
    @PostMapping(value = "/signup")
    public String register(@RequestBody SignUp SignUpRequest){
        boolean isSuccess = authService.signUp(SignUpRequest);
        return isSuccess ? "회원가입 되었습니다." : "회원가입에 실패하였습니다.";
    }

    @ApiOperation("로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDto.Login login, HttpServletResponse httpServletResponse){

        //String token = authService.login(loginRequest);
        //httpServletResponse.setHeader("X-AUTH-TOKEN",token);
        System.out.println(login.getUserid());
        return authService.login(login,httpServletResponse);
    }

    @ApiOperation("회원 탈퇴")
    @PostMapping("signout/{id}")
    public ResponseEntity<?> signOut(@PathVariable Integer id){

        return authService.signOut(id);
    }

    @ApiOperation("헤더 유지")
    @GetMapping("/header")
    public void testRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/user");
    }


    @GetMapping("/user")
    public String getMyUserInfo(HttpServletRequest request) {
        return "header";
    }


} // end class
