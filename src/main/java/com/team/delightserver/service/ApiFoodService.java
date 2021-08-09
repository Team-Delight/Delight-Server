package com.team.delightserver.service;

import com.team.delightserver.web.domain.food.FoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @CreateBy:Min
 * @Date: 2021/08/02
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiFoodService {

    private final FoodRepository foodRepository;

    // 2차 작성예정 부분
//    public List<Food> foods = foodRepository.findAllByCategoryId();
}