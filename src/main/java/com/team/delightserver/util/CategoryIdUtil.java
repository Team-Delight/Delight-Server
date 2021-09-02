package com.team.delightserver.util;

import com.team.delightserver.util.enumclass.RedisCommonConstant;
import com.team.delightserver.web.domain.category.Category;
import com.team.delightserver.web.domain.category.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Created by Bloo
 * @Date: 2021/08/28
 *
 * description
 * - 추천 랭킹 캐싱 스케줄러, 설문조사 캐싱 스케줄러 동작시 categoryId 별로 캐싱을 진행 합니다.
 * - 이때 Id 값을 동적으로 바꿀 필요가 있었고 이를 위해서 해당 클래스를 만들었습니다.
 *
 * 로직
 *  - 먼저 Category Repository 에서 전체 category를 조회하고 Id만 따로 빼고 이를 Id List에
 *  - 다시 초기화 합니다
 */

@RequiredArgsConstructor
@Getter
@Component
public class CategoryIdUtil {

    private final CategoryRepository categoryRepository;
    private List<Long> categoryIds = new ArrayList<>();

    @PostConstruct
    private void setCategoryIds() {
        categoryIds = categoryRepository.findAll()
            .stream()
            .map(Category::getId)
            .collect(Collectors.toList());
    }
}
