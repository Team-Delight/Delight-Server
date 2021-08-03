package com.team.delightserver.web.domain.food;

import com.team.delightserver.web.domain.Timestamped;
import com.team.delightserver.web.domain.category.Category;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @CreateBy:Min
 * @Date: 2021/08/02
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Food extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Size(max = 10)
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private String imgUrl;

    @NonNull
    @Column(nullable = false)
    private String introduce;

    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Builder
    public Food(Long id, @NonNull String name, @NonNull String imgUrl,
                @NonNull String introduce, Category category) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.introduce = introduce;
        this.category = category;
    }
}
