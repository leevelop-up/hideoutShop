package com.example.hideoutshop.web.filters;

import com.example.hideoutshop.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    //private final TokenRepository tokenRepository;

    // request에서 토큰을 추출한 후 해당 토큰이 통과하면 권한을 부여하고, 실패하면 권한을 부여하지 않고 다음 필터로 진행시킨다
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = jwtTokenProvider.resolveToken(request);

        // 유효한 토큰일 시 인증
        if (jwtToken != null && jwtTokenProvider.validateToken(jwtToken)) {
            Authentication auth = jwtTokenProvider.getAuthentication(jwtToken);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
