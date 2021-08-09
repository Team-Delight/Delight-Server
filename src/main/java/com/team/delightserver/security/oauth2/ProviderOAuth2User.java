package com.team.delightserver.security.oauth2;

import com.team.delightserver.util.enumclass.Role;
import com.team.delightserver.util.enumclass.Social;
import com.team.delightserver.web.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProviderOAuth2User implements OAuth2User {
    private static final String NAME_ATTRIBUTE_KEY = "name";
    public static final String SOCIAL_PROVIDER_KEY = "socialProviderKey";

    protected String name;
    protected String email;
    protected String socialProviderKey;
    protected String profileImg;
    protected String OAuthProvider;
    protected Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
    protected Map<String, Object> attributes = new HashMap<>();

    public ProviderOAuth2User(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.socialProviderKey = user.getSocialProviderKey();
        this.profileImg = user.getProfileImg();
        this.OAuthProvider = user.getSocial().toString();
    }

    public User toUser() {
        return User.builder()
                .name(name)
                .email(email)
                .socialProviderKey(socialProviderKey)
                .profileImg(profileImg)
                .role(Role.USER)
                .social(Social.valueOf(OAuthProvider.toUpperCase())).build();
    }

    public Map<String, Object> createJWTPayload(){
        return Map.of
            (
                NAME_ATTRIBUTE_KEY, name,
                SOCIAL_PROVIDER_KEY, socialProviderKey
            );
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public <A> A getAttribute(String name) {
        return (A) attributes.get(name);
    }
}
