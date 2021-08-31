package com.team.delightserver.security.oauth2;

import java.util.Map;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 * @ModifiedDate: 2021/08/19, 2021/08/30
 */

public class GoogleOAuth2UserProvider extends OAuth2UserProvider {

    public static final String ATTRIBUTES_NAME = "name";
    public static final String ATTRIBUTES_EMAIL = "email";
    public static final String ATTRIBUTES_KEY = "sub";
    public static final String ATTRIBUTES_PICTURE = "picture";

    public GoogleOAuth2UserProvider(String OAuthProvider, Map<String, Object> providedAttributes) {
        super.attributes = providedAttributes;
        super.name = String.valueOf(attributes.get(ATTRIBUTES_NAME));
        super.email = String.valueOf(attributes.get(ATTRIBUTES_EMAIL));
        super.socialProviderKey = String.valueOf(attributes.get(ATTRIBUTES_SUB));
        super.profileImg = String.valueOf(attributes.get(ATTRIBUTES_PICTURE));
        super.OAuthProvider = OAuthProvider;
    }
}
