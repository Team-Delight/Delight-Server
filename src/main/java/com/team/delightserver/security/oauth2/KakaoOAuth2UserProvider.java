package com.team.delightserver.security.oauth2;

import com.sun.tools.javac.util.List;
import com.team.delightserver.exception.security.InvalidOAuth2AttributesException;

import java.util.Map;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 * @ModifiedDate: 2021/08/19, 2021/08/30
 */

public class KakaoOAuth2UserProvider extends OAuth2UserProvider {

    public static final String ATTRIBUTES = "properties";
    public static final String ATTRIBUTES_NAME = "nickname";
    public static final String ATTRIBUTES_EMAIL = "email";
    public static final String ATTRIBUTES_KEY = "id";
    public static final String ATTRIBUTES_PICTURE = "profile_image";

    public KakaoOAuth2UserProvider(String OAuthProvider, Map<String, Object> providedAttributes) {
        try{
            super.attributes = (Map<String, Object>) providedAttributes.get(ATTRIBUTES);

            if (!providedAttributes.keySet().containsAll(List.of(ATTRIBUTES_KEY)) ||
                !attributes.keySet().containsAll(List.of(ATTRIBUTES_NAME, ATTRIBUTES_EMAIL, ATTRIBUTES_PICTURE))) {
                throw new InvalidOAuth2AttributesException();
            }

            super.name = String.valueOf(attributes.get(ATTRIBUTES_NAME));
            super.email = String.valueOf(attributes.get(ATTRIBUTES_EMAIL));
            super.socialProviderKey = String.valueOf(providedAttributes.get(ATTRIBUTES_KEY));
            super.profileImg = String.valueOf(attributes.get(ATTRIBUTES_PICTURE));
            super.OAuthProvider = OAuthProvider;
        }
        catch (NullPointerException nullPointerException){
            throw new InvalidOAuth2AttributesException(nullPointerException);
        }
    }
}
