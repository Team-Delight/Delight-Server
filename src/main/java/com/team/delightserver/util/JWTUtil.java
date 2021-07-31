package com.team.delightserver.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

@Component
public class JWTUtil {
    private String SECRET_KEY = "secret";
    private SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public <T extends Object> T extractFromClaims(Claims claims, String key){
        Object value = claims.get(key);
        return (T) value;
    }

    public Boolean isTokenValid(String token) {
        try{
            return !extractAllClaims(token).getExpiration().before(new Date());
        } catch (MalformedJwtException | ExpiredJwtException exception){
            return false;
        }
    }

    public String generateToken(Map<String, Object> payload, String subject, Long jwtDueMillis) {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", ALGORITHM.getValue());

        Date expireDate = new Date(new Date().getTime() + jwtDueMillis);

        return Jwts.builder()
                .setHeader(header)
                .setClaims(new HashMap<>(payload))
                .setSubject(subject)
                .setExpiration(expireDate)
                .signWith(ALGORITHM, SECRET_KEY)
                .compact();
    }
}
