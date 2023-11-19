package com.example.weguide.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    // JWT에서 사용자 이름 추출
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // JWT 만료 일자 추출
    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // JWT에서 클레임 추출
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // JWT에서 모든 클레임 추출
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // JWT가 만료되었는지 확인
    private Boolean isTokenExpired(String token) {
        final Date expiration = extractExpirationDate(token);
        return expiration.before(new Date());
    }

    // JWT 생성
    public String generateToken(String userid) {
        return Jwts.builder()
                .setSubject(userid)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // JWT 유효성 검사
    public int validateToken(String token) {
    	
    	try {
            if(!isTokenExpired(token)) {
            	return 1; //정상 로그인
            }else {
            	return 0; //토큰이 만료됨
            }
        } catch (Exception e) {
            // 예외가 발생하면 false를 반환하거나 다른 처리를 수행할 수 있습니다.
        	System.out.println(e);
            return 2;
            //토큰이 일치하지 않음
        }
    }
}
