package com.team.delightserver.web.domain.food;

import com.team.delightserver.web.domain.BaseTimeEntity;
import com.team.delightserver.web.domain.category.Category;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;


/**
 * @CreateBy:Min
 * @Date: 2021/08/02
 */
@EqualsAndHashCode(of = "id", callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Food extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Size(max = 10)
    @Column(nullable = false)
    private String name;

    @Lob
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
    public Food ( String name, String imgUrl, String introduce, Category category ) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.introduce = introduce;
        this.category = category;
    }

    public static Food of(String name, String imgUrl,String introduce, Category category) {
        return Food.builder()
                .name(name)
                .imgUrl(imgUrl)
                .introduce(introduce)
                .category(category)
                .build();
    }

    public RedisCacheFood toRedisCacheFood() {
        return RedisCacheFood.builder()
            .foodId(id)
            .name(name)
            .imgUrl(imgUrl)
            .build();
    }
}
