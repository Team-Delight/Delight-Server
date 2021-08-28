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
