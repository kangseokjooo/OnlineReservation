package com.kangsukju.reservation_system.Security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final String token;

    public JwtAuthenticationToken(String token) {
        super(null);  // 권한은 나중에 설정
        this.token = token;
        setAuthenticated(true);  // JWT 토큰이 유효하다면 인증됨
    }

    @Override
    public Object getCredentials() {
        return token;  // 토큰을 자격 증명으로 반환
    }

    @Override
    public Object getPrincipal() {
        return null;  // JWT 토큰에서 사용자 정보를 추출하여 principal로 설정할 수 있음
    }
}
