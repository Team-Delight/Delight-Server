package com.team.delightserver.web.controller;

import com.team.delightserver.web.dto.response.TopTenFoodCategoryResponseDto;
import com.team.delightserver.web.service.ApiFoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @CreateBy:Min
 * @Date: 2021/08/02
 */

@Slf4j
@RequiredArgsConstructor
@RestController
public class ApiFoodController {

    private final ApiFoodService foodService;

    @GetMapping("/api/{categoryId}/recommendations")
    public ResponseEntity<List<TopTenFoodCategoryResponseDto>> findTopTenFoodsByCategoryId(@PathVariable Long categoryId) {
        log.info("Category Id: {}", categoryId);
        // 3차 작성 부분
        return ResponseEntity.ok().body(foodService.findAllBy.CategoryId(categoryId));
    }
}
