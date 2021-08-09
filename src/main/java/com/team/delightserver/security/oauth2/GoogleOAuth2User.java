package com.team.delightserver.security.oauth2;

import java.util.Map;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

public class GoogleOAuth2User extends ProviderOAuth2User {

    private static final String ATTRIBUTES_NAME = "name";
    private static final String ATTRIBUTES_EMAIL = "email";
    private static final String ATTRIBUTES_SUB = "sub";
    private static final String ATTRIBUTES_PICTURE = "picture";

    public GoogleOAuth2User(String OAuthProvider, Map<String, Object> providedAttributes) {
        super.attributes = providedAttributes;
        super.name = String.valueOf(attributes.get(ATTRIBUTES_NAME));
        super.email = String.valueOf(attributes.get(ATTRIBUTES_EMAIL));
        super.socialProviderKey = String.valueOf(attributes.get(ATTRIBUTES_SUB));
        super.profileImg = String.valueOf(attributes.get(ATTRIBUTES_PICTURE));
        super.OAuthProvider = OAuthProvider;
    }
}
