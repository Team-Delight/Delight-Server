package com.team.delightserver.security.jwt;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 * @ModifiedDate : 2021/08/30
 */

public class JwtTokenProvider {
    private static final String SECRET_KEY = "secret";
    private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;

    public static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public static <T> T extractFromClaims(Claims claims, String key){
        Object value = claims.get(key);
        return (T) value;
    }

    public static Boolean isTokenValid(String token) {
        try{
            extractAllClaims(token);
            return true;
        } catch (MalformedJwtException | ExpiredJwtException exception){
            return exception.getClass().equals(ExpiredJwtException.class);
        }
    }

    public static Boolean isTokenExpired(String token) throws MalformedJwtException{
        try{
            extractAllClaims(token);
            return false;
        } catch (ExpiredJwtException exception){
            return true;
        }
    }

    public static String generateToken(Map<String, Object> payload, String subject, Long jwtDueMillis) {
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
