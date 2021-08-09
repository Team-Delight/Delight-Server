package com.team.delightserver.web.domain.category;

import com.team.delightserver.web.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Created by Bloo , Min
 * @Date: 2021/08/05,  2021/08/02
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity

public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @Builder
    public Category(@NonNull String name) {
        this.name = name;
    }

    public static Category of(String name) {
        return Category.builder()
                .name(name)
                .build();
    }
}
