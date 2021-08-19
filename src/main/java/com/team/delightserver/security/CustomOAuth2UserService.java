package com.team.delightserver.security;

import com.team.delightserver.security.oauth2.factory.OAuth2UserProviderFactory;
import com.team.delightserver.security.oauth2.OAuth2UserProvider;
import com.team.delightserver.web.domain.user.User;
import com.team.delightserver.web.domain.user.UserRepository;
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
 * @ModifiedDate: 2021/08/19
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
        OAuth2UserProvider refactoredOAuth2User = OAuth2UserProviderFactory
            .of(OAuthProvider, defaultOAuth2User.getAttributes());
        saveOrUpdate(refactoredOAuth2User);

        return refactoredOAuth2User;
    }

    private void saveOrUpdate(OAuth2UserProvider OAuth2UserProvider) {
        User user = userRepository.findBySocialProviderKey(OAuth2UserProvider.getSocialProviderKey())
                .map(savedUser -> savedUser.update(OAuth2UserProvider.getName(), OAuth2UserProvider.getEmail(), OAuth2UserProvider.getProfileImg()))
                .orElse(OAuth2UserProvider.toUser());

        userRepository.save(user);
    }
}