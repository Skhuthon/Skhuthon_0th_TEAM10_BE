package com.example.skhuthon_0th_team10.jwt;


import com.example.skhuthon_0th_team10.domain.User;
import com.example.skhuthon_0th_team10.dto.Token;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {
    private final Key key;
    private final long accessTokenValidityTime;

    public TokenProvider(@Value("${jwt.secret}") String secretKey,
                         @Value("${jwt.access-token-validity-in-milliseconds}") long accessTokenValidityTime) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenValidityTime = accessTokenValidityTime;
    }

    public Token createToken(User user) {
        long nowTime = (new Date()).getTime();

        Date tokenExpiredTime = new Date(nowTime + accessTokenValidityTime);

        String accessToken = Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("auth", user.getRole().name())
                .setExpiration(tokenExpiredTime)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return Token.builder()
                .accessToken(accessToken)
                .build();
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 위 과정을 통과하면 권한 정보가 있는 토큰임

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        // 권한 만드는 stream. ,로 구분해서 SimpleGrantedAuthority로 만들고 리스트로 뽑음.
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), "", authorities);
        // 사용자 이름 비밀번호 권한을 담음 객체를 만듦.
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (UnsupportedJwtException | ExpiredJwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
            // Jwts 라이브러리를 사용하여 JWT 토큰을 파싱하고, 그 결과를 반환.
        } catch (ExpiredJwtException e) {
            // 만약 JWT 토큰이 만료된 경우, 그 토큰의 Claims 객체를 반환.
            return e.getClaims();
        }
    }
}
