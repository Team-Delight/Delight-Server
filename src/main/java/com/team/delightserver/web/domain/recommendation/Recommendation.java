package com.team.delightserver.web.domain.recommendation;

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
public class Recommendation extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int count;

    @Builder
    public Recommendation(Long id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public static Recommendation of (String name, int count) {
        return Recommendation.builder()
                .name(name)
                .count(count)
                .build();
    }

    public void addCount(int count) {
        this.count = count;
    }
}
