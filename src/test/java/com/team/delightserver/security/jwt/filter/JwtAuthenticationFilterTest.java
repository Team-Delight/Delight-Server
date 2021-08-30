package com.team.delightserver.security.jwt.filter;

import com.team.delightserver.security.jwt.JwtTokenProvider;
import com.team.delightserver.security.oauth2.OAuth2UserProvider;
import com.team.delightserver.exception.security.InvalidJwtTokenException;
import com.team.delightserver.util.CustomRandomUtil;
import com.team.delightserver.util.enumclass.Role;
import com.team.delightserver.util.enumclass.Social;
import com.team.delightserver.web.domain.user.User;
import com.team.delightserver.web.domain.user.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @CreateBy: Doe
 * @Date: 2021/08/27
 */

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JwtAuthenticationFilterTest {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private UserRepository userRepository;

    private MockHttpServletResponse mockHttpServletResponse;
    private MockHttpServletRequest mockHttpServletRequest;
    private MockFilterChain mockFilterChain;

    private static final String SUBJECT= "user";
    private static final String JWT_HEADER_KEY = "Authorization";
    private static final User validUser = new User(
            1L,
            "김도형",
            "dohyung97022@gmail.com",
             CustomRandomUtil.getRandomString(),
            null,
            Social.GOOGLE,
            Role.USER
    );

    //Given
    private static final String INVALID_JWT = CustomRandomUtil.getRandomString();
    private static final String EXPIRED_JWT = JwtTokenProvider.generateToken(new HashMap<>(), SUBJECT, -1L);
    private static final String VALID_JWT = JwtTokenProvider.generateToken(
            new OAuth2UserProvider(validUser).createJWTPayload(),
            SUBJECT,
            TimeUnit.DAYS.toMillis(1000L)
    );

    @BeforeAll
    public void beforeAll(){
        userRepository.save(validUser);
    }

    @BeforeEach
    public void beforeEach() {
        mockHttpServletResponse = new MockHttpServletResponse();
        mockHttpServletRequest = new MockHttpServletRequest();
        mockFilterChain = new MockFilterChain();
    }

    @Nested
    class doFilterInternal {

        @Test
        void validToken() {
            // Given
            // When
            mockHttpServletRequest.addHeader(JWT_HEADER_KEY, VALID_JWT);
            // Then
            Assertions.assertDoesNotThrow(() -> jwtAuthenticationFilter.doFilterInternal(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain));
            Assertions.assertNotNull(SecurityContextHolder.getContext().getAuthentication());
            Assertions.assertTrue(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        }

        @Test
        void inValidToken() {
            //Given
            //When
            mockHttpServletRequest.addHeader(JWT_HEADER_KEY, INVALID_JWT);
            //Then
            Assertions.assertThrows(
                    InvalidJwtTokenException.class,
                    () -> jwtAuthenticationFilter.doFilterInternal(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain)
            );
            Assertions.assertNull(SecurityContextHolder.getContext().getAuthentication());
        }

        @Test
        void expiredToken() {
            //Given
            //When
            mockHttpServletRequest.addHeader(JWT_HEADER_KEY, EXPIRED_JWT);
            //Then
            Assertions.assertDoesNotThrow(() -> jwtAuthenticationFilter.doFilterInternal(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain));
            Assertions.assertNull(SecurityContextHolder.getContext().getAuthentication());
        }
    }
}