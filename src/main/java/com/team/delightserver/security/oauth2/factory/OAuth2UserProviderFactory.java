package com.team.delightserver.security.oauth2.factory;

import com.team.delightserver.security.exception.InvalidOauth2ProviderException;
import com.team.delightserver.security.oauth2.GoogleOAuth2UserProvider;
import com.team.delightserver.security.oauth2.KakaoOAuth2UserProvider;
import com.team.delightserver.security.oauth2.NaverOAuth2UserProvider;
import com.team.delightserver.util.enumclass.Social;
import java.util.Map;
import com.team.delightserver.security.oauth2.OAuth2UserProvider;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 * @ModifiedDate: 2021/08/19
 */

public class OAuth2UserProviderFactory {

    public static OAuth2UserProvider of (String OAuthProvider, Map<String, Object> attributes ) {
        switch ( Social.valueOf(OAuthProvider.toUpperCase()) ) {
            case NAVER:
                return new NaverOAuth2UserProvider(OAuthProvider, attributes);
            case KAKAO:
                return new KakaoOAuth2UserProvider(OAuthProvider, attributes);
            case GOOGLE:
                return new GoogleOAuth2UserProvider(OAuthProvider, attributes);
            default:
                throw new InvalidOauth2ProviderException();
        }
    }
}
