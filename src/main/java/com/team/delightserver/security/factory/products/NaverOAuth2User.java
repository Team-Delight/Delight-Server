package com.team.delightserver.security.factory.products;

import java.util.Map;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

public class NaverOAuth2User extends ProviderOAuth2User {

    public NaverOAuth2User(String OAuthProvider, Map<String, Object> providedAttributes) {
        super.attributes = (Map<String, Object>) providedAttributes.get("response");
        super.name = String.valueOf(attributes.get("name"));
        super.email = String.valueOf(attributes.get("email"));
        super.socialProviderKey = String.valueOf(attributes.get("id"));
        super.profileImg = String.valueOf(attributes.get("profile_image"));
        super.OAuthProvider = OAuthProvider;
    }
}
