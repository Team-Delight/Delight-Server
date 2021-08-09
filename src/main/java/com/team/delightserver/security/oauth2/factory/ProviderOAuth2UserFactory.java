package com.team.delightserver.security.oauth2.factory;

import com.team.delightserver.security.oauth2.exception.InvalidOauth2ProviderException;
import com.team.delightserver.security.oauth2.GoogleOAuth2User;
import com.team.delightserver.security.oauth2.KakaoOAuth2User;
import com.team.delightserver.security.oauth2.NaverOAuth2User;
import com.team.delightserver.util.enumclass.Social;
import java.util.Map;
import com.team.delightserver.security.oauth2.ProviderOAuth2User;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

public class ProviderOAuth2UserFactory {

    public static ProviderOAuth2User of ( String OAuthProvider, Map<String, Object> attributes ) {
        switch ( Social.valueOf(OAuthProvider.toUpperCase()) ) {
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
