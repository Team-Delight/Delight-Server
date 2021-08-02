package com.team.delightserver.web.domain.food;

import com.team.delightserver.web.domain.Timestamped;
import lombok.*;

import javax.persistence.*;

/**
 * @CreateBy:Min
 * @Date: 2021/08/02
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Category extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @Builder
    public Category(Long id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }
}
