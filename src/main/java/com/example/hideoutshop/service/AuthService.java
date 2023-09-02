package com.example.hideoutshop.service;

import com.example.hideoutshop.config.JwtTokenProvider;
import com.example.hideoutshop.repository.member.Member;
import com.example.hideoutshop.repository.member.MemberRepository;
import com.example.hideoutshop.service.exceptions.NotAcceptException;
import com.example.hideoutshop.service.exceptions.NotFoundException;
import com.example.hideoutshop.web.dto.Login;
import com.example.hideoutshop.web.dto.SignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public boolean signUp(SignUp signUpRequest) {
        String email = signUpRequest.getEmail();
        String userid = signUpRequest.getUserid();
        String password = signUpRequest.getPassword();

        if(memberRepository.existsByEmail(email)){
            return false;

        }

//        Member memberFound = memberRepository.findByUserId(userid)
//                .orElseGet(()-> memberRepository.save(Member.builder().userId(userid).build()));

        Member member = Member.builder()
                .email(email)
                .userId(userid)
                .password(passwordEncoder.encode(password))
                .build();
        memberRepository.save(member);
        return true;
    }

    public String login(Login loginRequest) {
        String userid = loginRequest.getUserid();
        String password = loginRequest.getPassword();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userid,password)

            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Member member = memberRepository.findByUserId(userid)
                    .orElseThrow(()->new NotFoundException("user가 없습니다."));
            return jwtTokenProvider.createToken(userid,"role");
        }catch (Exception e){
            e.printStackTrace();
            throw new NotAcceptException("로그인에 실패했습니다.");
        }
    }
} // End class
