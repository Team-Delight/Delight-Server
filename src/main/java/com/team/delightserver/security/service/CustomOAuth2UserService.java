package com.team.delightserver.security.service;

import com.team.delightserver.security.factory.factory.ProviderOAuth2UserFactory;
import com.team.delightserver.security.factory.products.ProviderOAuth2User;
import com.team.delightserver.web.entity.User;
import com.team.delightserver.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> defaultOAuth2UserService = new DefaultOAuth2UserService();
        String OAuthProvider = userRequest.getClientRegistration().getRegistrationId();
        OAuth2User defaultOAuth2User = defaultOAuth2UserService.loadUser(userRequest);
        ProviderOAuth2User refactoredOAuth2User = ProviderOAuth2UserFactory.of(OAuthProvider, defaultOAuth2User.getAttributes());
        saveOrUpdate(refactoredOAuth2User);

        return refactoredOAuth2User;
    }

    private void saveOrUpdate(ProviderOAuth2User providerOAuth2User) {
        User user = userRepository.findBySocialProviderKey(providerOAuth2User.getSocialProviderKey())
                .map(savedUser -> savedUser.update(providerOAuth2User.getName(), providerOAuth2User.getEmail(), providerOAuth2User.getProfileImg()))
                .orElse(providerOAuth2User.toUser());

        userRepository.save(user);
    }
}