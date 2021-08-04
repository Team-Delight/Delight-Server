package com.team.delightserver.security.filter;

import com.team.delightserver.security.factory.products.ProviderOAuth2User;
import com.team.delightserver.util.JWTUtil;
import com.team.delightserver.web.entity.User;
import com.team.delightserver.web.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

/**
 * @Created by Doe
 * @Date: 2021/07/29
 */

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter{
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader("Authorization");

        if (jwtToken!=null && JWTUtil.isTokenValid(jwtToken)){
            Claims claims = JWTUtil.extractAllClaims(jwtToken);
            String socialProviderKey = JWTUtil.extractFromClaims(claims, ProviderOAuth2User.SOCIAL_PROVIDER_KEY);
            Optional<User> optionalUser = userRepository.findBySocialProviderKey(socialProviderKey);

            optionalUser.ifPresent((User user) -> {
                Set<GrantedAuthority> authoritySet = Set.of(new SimpleGrantedAuthority(user.getRole().toString()));
                ProviderOAuth2User providerOAuth2User = new ProviderOAuth2User(user);
                Authentication authenticationToken = new UsernamePasswordAuthenticationToken(providerOAuth2User, socialProviderKey, authoritySet);

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            });
        }
        filterChain.doFilter(request,response);
    }
}
