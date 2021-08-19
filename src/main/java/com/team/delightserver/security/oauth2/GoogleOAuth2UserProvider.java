package com.team.delightserver.security.oauth2;

import java.util.Map;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 * @ModifiedDate: 2021/08/19
 */

public class GoogleOAuth2UserProvider extends OAuth2UserProvider {

    private static final String ATTRIBUTES_NAME = "name";
    private static final String ATTRIBUTES_EMAIL = "email";
    private static final String ATTRIBUTES_SUB = "sub";
    private static final String ATTRIBUTES_PICTURE = "picture";

    public GoogleOAuth2UserProvider(String OAuthProvider, Map<String, Object> providedAttributes) {
        super.attributes = providedAttributes;
        super.name = String.valueOf(attributes.get(ATTRIBUTES_NAME));
        super.email = String.valueOf(attributes.get(ATTRIBUTES_EMAIL));
        super.socialProviderKey = String.valueOf(attributes.get(ATTRIBUTES_SUB));
        super.profileImg = String.valueOf(attributes.get(ATTRIBUTES_PICTURE));
        super.OAuthProvider = OAuthProvider;
    }
}
