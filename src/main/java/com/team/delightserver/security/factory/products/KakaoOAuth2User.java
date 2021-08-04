package com.team.delightserver.security.factory.products;

import java.util.Map;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

public class KakaoOAuth2User extends ProviderOAuth2User {

    public KakaoOAuth2User(String OAuthProvider, Map<String, Object> providedAttributes) {
        super.attributes = (Map<String, Object>) providedAttributes.get("properties");
        super.name = String.valueOf(attributes.get("nickname"));
        super.email = String.valueOf(attributes.get("email"));
        super.socialProviderKey = String.valueOf(providedAttributes.get("id"));
        super.profileImg = String.valueOf(attributes.get("profile_image"));
        super.OAuthProvider = OAuthProvider;
    }
}
