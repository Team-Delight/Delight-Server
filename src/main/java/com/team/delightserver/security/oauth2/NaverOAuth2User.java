package com.team.delightserver.security.oauth2;

import java.util.Map;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

public class NaverOAuth2User extends ProviderOAuth2User {

    private static final String ATTRIBUTES = "response";
    private static final String ATTRIBUTES_NAME = "name";
    private static final String ATTRIBUTES_EMAIL = "email";
    private static final String ATTRIBUTES_ID = "id";
    private static final String ATTRIBUTES_PROFILE_IMG = "profile_image";

    public NaverOAuth2User(String OAuthProvider, Map<String, Object> providedAttributes) {
        super.attributes = (Map<String, Object>) providedAttributes.get(ATTRIBUTES);
        super.name = String.valueOf(attributes.get(ATTRIBUTES_NAME));
        super.email = String.valueOf(attributes.get(ATTRIBUTES_EMAIL));
        super.socialProviderKey = String.valueOf(attributes.get(ATTRIBUTES_ID));
        super.profileImg = String.valueOf(attributes.get(ATTRIBUTES_PROFILE_IMG));
        super.OAuthProvider = OAuthProvider;
    }
}
