package com.team.delightserver.security.jwt.filter;

import com.team.delightserver.security.jwt.JwtTokenProvider;
import com.team.delightserver.security.oauth2.OAuth2UserProvider;
import com.team.delightserver.security.oauth2.exception.InvalidJwtTokenException;
import com.team.delightserver.web.domain.user.User;
import com.team.delightserver.web.domain.user.UserRepository;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @Created by Doe
 * @Date: 2021/07/29
 * @ModifiedDate: 2021/08/19, 2021/08/30
 */

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter{
    private final UserRepository userRepository;
    private static final String JWT_HEADER_KEY = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String jwtToken = request.getHeader(JWT_HEADER_KEY);

        if (jwtToken != null){
            if (!JwtTokenProvider.isTokenValid(jwtToken)){
                throw new InvalidJwtTokenException(jwtToken);
            }
            else if (!JwtTokenProvider.isTokenExpired(jwtToken)){
                Claims claims = JwtTokenProvider.extractAllClaims(jwtToken);
                String socialProviderKey = JwtTokenProvider.extractFromClaims(claims, OAuth2UserProvider.SOCIAL_PROVIDER_KEY);
                Optional<User> optionalUser = userRepository.findBySocialProviderKey(socialProviderKey);

                optionalUser.ifPresent((User user) -> {
                    Set<GrantedAuthority> authoritySet = Set.of(new SimpleGrantedAuthority(user.getRole().toString()));
                    OAuth2UserProvider OAuth2UserProvider = new OAuth2UserProvider(user);
                    Authentication authenticationToken = new UsernamePasswordAuthenticationToken(OAuth2UserProvider, socialProviderKey, authoritySet);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                });
            }
        }

        filterChain.doFilter(request,response);
    }
}