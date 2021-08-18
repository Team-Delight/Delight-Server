package com.team.delightserver.web.domain.mypick;

import com.team.delightserver.web.domain.BaseTimeEntity;
import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

/**
 * @Created by Doe
 * @Date: 2021/08/17
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "MYPICK")
public class MyPick extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @NonNull
    @JoinColumn(name = "food_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;
}