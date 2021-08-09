package com.team.delightserver.web.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    private String password;

    private String socialProviderKey;

    private String introduce;

    private String profileImg;

    @Enumerated(EnumType.STRING)
    private Social social;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String name, String email, String password, String socialProviderKey, String introduce, String profileImg, Role role, Social social) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.socialProviderKey = socialProviderKey;
        this.introduce = introduce;
        this.profileImg = profileImg;
        this.role = role;
        this.social = social;
    }

    public User update(String name, String email, String profileImg) {
        this.name = name;
        this.email = email;
        this.profileImg = profileImg;
        return this;
    }
}