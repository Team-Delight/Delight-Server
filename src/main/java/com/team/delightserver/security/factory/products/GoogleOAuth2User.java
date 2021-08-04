package com.team.delightserver.security.factory.products;

import java.util.Map;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

public class GoogleOAuth2User extends ProviderOAuth2User {

    public GoogleOAuth2User(String OAuthProvider, Map<String, Object> providedAttributes) {
        super.attributes = providedAttributes;
        super.name = String.valueOf(attributes.get("name"));
        super.email = String.valueOf(attributes.get("email"));
        super.socialProviderKey = String.valueOf(attributes.get("sub"));
        super.profileImg = String.valueOf(attributes.get("picture"));
        super.OAuthProvider = OAuthProvider;
    }
}
