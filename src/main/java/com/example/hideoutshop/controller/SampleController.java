package com.example.hideoutshop.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api")
public class SampleController {

    @GetMapping("/generate-token")
    public String generateToken2(HttpServletResponse httpServletResponse){
        String jwt = Jwts.builder()
                .setSubject("token1")
                .claim("user", "송혜교")
                .claim("gender", "여자")
                .claim("job", "배우")
                .compact();
        httpServletResponse.addHeader("Token", jwt);
        return "JWT set Successfully";
    }
    @GetMapping("/show-token")
    public String showToken(@RequestHeader("Token") String token){
        Claims claims = Jwts.parser()
                .parseClaimsJwt(token)
                .getBody();

        String user = (String) claims.get("user");
        String gender = (String) claims.get("gender");
        String job = (String) claims.get("job");

        return String.format(user+"/"+gender+"/"+job);
    }


}
