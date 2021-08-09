package com.team.delightserver.util.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

@AllArgsConstructor
@Getter
public enum Role {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String key;
}