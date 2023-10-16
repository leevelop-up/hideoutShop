package com.example.hideoutshop.service;

import com.example.hideoutshop.config.JwtTokenProvider;
import com.example.hideoutshop.repository.member.Member;
import com.example.hideoutshop.repository.member.MemberRepository;
import com.example.hideoutshop.repository.member.Role;
import com.example.hideoutshop.service.exceptions.NotAcceptException;
import com.example.hideoutshop.service.exceptions.NotFoundException;
import com.example.hideoutshop.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final Response response;
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final RedisTemplate redisTemplate;

    private static final String BEARER_TYPE = "Bearer ";


    public boolean signUp(SignUp signUpRequest) {
        String email = signUpRequest.getEmail();
        String userid = signUpRequest.getUserid();
        String password = signUpRequest.getPassword();
        LocalDateTime now = LocalDateTime.now();
        if(email == "" || userid == "" || password == ""){
            return false;
        }
        if(memberRepository.existsByEmail(email)){
            return false;
        }

        validateDuplicateMember(userid);
        Member member = Member.builder()
                .email(email)
                .userId(userid)
                .password(passwordEncoder.encode(password))
                .role((userid.equals("admin") ? Role.ROLE_ADMIN : Role.ROLE_USER))
                .joindate(now)
                .build();
        memberRepository.save(member);
        return true;
    }

    private void validateDuplicateMember(String userid) {
        memberRepository.findByUserId(userid)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 값이 있습니다");
                });
    }

    public ResponseEntity<?> login(UserRequestDto.Login login, HttpServletResponse httpServletResponse) {
        String userid = login.getUserid();
        String password = login.getPassword();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userid,password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            memberRepository.findByUserId(userid)
                    .orElseThrow(()->new NotFoundException("user가 없습니다."));

            //TODO:토큰 비교후 아이디 추출 하는 함수 하나로 만들기
            UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.createToken(authentication);
            httpServletResponse.addHeader("Authorization","Bearer "+tokenInfo.getAccessToken());

//            redisTemplate.opsForValue()
//                    .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);
            return response.success(tokenInfo, "로그인에 성공했습니다.", HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            throw new NotAcceptException("로그인에 실패했습니다.");
        }
    }

    public ResponseEntity<?> signOut(Integer id) {

        try{
            memberRepository.findById(id).orElseThrow(()-> new NotFoundException("해당 값이 없습니다."));
            memberRepository.deleteById(id);

            return response.success("삭제처리 되었습니다.");
        }catch (Exception e){
            throw new NotAcceptException("삭제 실패했습니다.");

        }

    }



} // End class
