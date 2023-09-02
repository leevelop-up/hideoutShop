package com.example.hideoutshop.service.security;

import com.example.hideoutshop.repository.member.Member;
import com.example.hideoutshop.repository.member.MemberRepository;
import com.example.hideoutshop.repository.userDetails.CustomerUserDetails;
import com.example.hideoutshop.service.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Primary
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByUserId(email).orElseThrow(()-> new NotFoundException("email에 해당하는 회원이 없습니다."));
        CustomerUserDetails customerUserDetails = CustomerUserDetails.builder()
                .userId(member.getUserId())
                .email(member.getEmail())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("role")))
                .password(member.getPassword()).build();
        return customerUserDetails;
    }
}
