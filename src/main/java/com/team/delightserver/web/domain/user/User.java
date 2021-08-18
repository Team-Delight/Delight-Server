package com.team.delightserver.web.domain.user;

import com.team.delightserver.util.enumclass.Role;
import com.team.delightserver.util.enumclass.Social;
import com.team.delightserver.web.domain.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @NonNull
    @Column(nullable = false)
    private String socialProviderKey;

    @Column
    private String profileImg;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Social social;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User (@NonNull Long id, @NonNull String name, String email, @NonNull String socialProviderKey
        , String profileImg, @NonNull Social social, @NonNull Role role ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.socialProviderKey = socialProviderKey;
        this.profileImg = profileImg;
        this.social = social;
        this.role = role;
    }


    public User update(String name, String email, String profileImg) {
        this.name = name;
        this.email = email;
        this.profileImg = profileImg;
        return this;
    }
}