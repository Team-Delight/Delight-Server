package com.team.delightserver.security.factory.factory;

import com.team.delightserver.security.exception.InvalidOauth2ProviderException;
import com.team.delightserver.security.factory.products.GoogleOAuth2User;
import com.team.delightserver.security.factory.products.KakaoOAuth2User;
import com.team.delightserver.security.factory.products.NaverOAuth2User;
import com.team.delightserver.security.factory.products.ProviderOAuth2User;
import com.team.delightserver.web.entity.Social;

import java.util.Map;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

public class ProviderOAuth2UserFactory {
    public static ProviderOAuth2User create(String OAuthProvider, Map<String, Object> attributes){
        switch (Social.valueOf(OAuthProvider.toUpperCase())) {
            case NAVER:
                return new NaverOAuth2User(OAuthProvider, attributes);
            case KAKAO:
                return new KakaoOAuth2User(OAuthProvider, attributes);
            case GOOGLE:
                return new GoogleOAuth2User(OAuthProvider, attributes);
            default:
                throw new InvalidOauth2ProviderException();
        }
    }
}
