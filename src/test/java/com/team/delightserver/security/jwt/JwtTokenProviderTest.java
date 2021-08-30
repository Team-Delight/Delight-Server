package com.team.delightserver.security.jwt;

import com.team.delightserver.util.CustomRandomUtil;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @CreateBy: Doe
 * @Date: 2021/08/27
 */

class JwtTokenProviderTest {
    private final HashMap<String, Object> emptyPayload = new HashMap<>();
    private final String SUBJECT= "user";

    //Given
    private final String VALID_JWT = JwtTokenProvider.generateToken(emptyPayload, SUBJECT, 1000L);
    private final String INVALID_JWT = CustomRandomUtil.getRandomString();
    private final String EXPIRED_JWT = JwtTokenProvider.generateToken(emptyPayload, SUBJECT, -1L);

    @Nested
    class isTokenValid {
        @Test
        void validToken() {
            //Given
            //When
            //Then
            Assertions.assertTrue(JwtTokenProvider.isTokenValid(VALID_JWT));
        }

        @Test
        void inValidToken() {
            //Given
            //When
            //Then
            Assertions.assertFalse(JwtTokenProvider.isTokenValid(INVALID_JWT));
        }

        @Test
        void expiredToken() {
            //Given
            //When
            //Then
            Assertions.assertTrue(JwtTokenProvider.isTokenValid(EXPIRED_JWT));
        }
    }

    @Nested
    class isTokenExpired {
        @Test
        void validToken() {
            //Given
            //When
            //Then
            Assertions.assertFalse(JwtTokenProvider.isTokenExpired(VALID_JWT));
        }

        @Test
        void inValidToken() {
            //Given
            //When
            //Then
            Assertions.assertThrows(MalformedJwtException.class,() -> JwtTokenProvider.isTokenExpired(INVALID_JWT));
        }

        @Test
        void expiredToken() {
            //Given
            //When
            //Then
            Assertions.assertTrue(JwtTokenProvider.isTokenExpired(EXPIRED_JWT));
        }
    }
}