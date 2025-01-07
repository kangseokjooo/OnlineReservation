package com.kangsukju.reservation_system.Security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String createToken(String userid) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);  // 만료 시간 설정

        return Jwts.builder()
                .setSubject(userid)
                .setExpiration(expirationDate)  // 만료 날짜 설정
                .signWith(SECRET_KEY)
                .compact();
    }  // 실제 환경에서는 이 값을 안전하게 관리해야 합니다.

    private long expirationTime = 1000 * 60 * 60;

    // JWT에서 사용자 이름 추출
    public String extractUserid(String token) {
        return extractClaim(token, Claims::getSubject);  // Claims에서 사용자 이름을 추출
    }

    // JWT에서 클레임 추출
    public <T> T extractClaim(String token, ClaimsResolver<T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.resolve(claims);
    }

    // JWT에서 모든 클레임을 추출
    private Claims extractAllClaims(String token) {
        // JWT 파싱 후 Claims 객체 반환
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)  // 서명 키 설정
                .build()
                .parseClaimsJws(token)  // JWT 파싱
                .getBody();  // Claims 반환
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    // 토큰 만료 여부 확인
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 토큰에서 만료 날짜 추출
    private Date extractExpiration(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        if (expiration == null) {
            throw new IllegalArgumentException("토큰 만기 X");
        }
        return expiration;
    }

    // ClaimsResolver 인터페이스 정의
    public interface ClaimsResolver<T> {
        T resolve(Claims claims);
    }
}
