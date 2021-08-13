package com.team.delightserver.web.controller;

import com.team.delightserver.service.ApiFoodService;
import com.team.delightserver.web.dto.response.RandomFoodsResponse;
import com.team.delightserver.web.dto.response.TagRelatedFoodsResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by Bloo, Doe
 * @Date: 2021/08/09, 2021/08/11
 * @ModifiedDate : 2021/08/11
 */

@Slf4j
@RequestMapping("/api/foods")
@RequiredArgsConstructor
@RestController
public class ApiFoodController {

    private final ApiFoodService apiFoodService;

    /**
     *  최근 먹은 음식 설문을 위한 데이터 리스트를 반환 합니다.
     */
    @GetMapping ("")
    public ResponseEntity<List<RandomFoodsResponse>> findRandomFoodsForSurvey() {
        return ResponseEntity.ok().body(apiFoodService.findRandomFoodsForSurvey());
    }

    @GetMapping("/tags/{tagId}")
    public ResponseEntity<List<TagRelatedFoodsResponse>> findFoodsByTag(@PathVariable Long tagId, Pageable pageable) {
        return ResponseEntity.ok().body(apiFoodService.findFoodsByTag(tagId, pageable));
    }
}
