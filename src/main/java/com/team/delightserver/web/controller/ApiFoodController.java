package com.team.delightserver.web.controller;

import com.team.delightserver.service.ApiFoodService;
import com.team.delightserver.web.dto.request.FindFoodsByTagsRequest;
import com.team.delightserver.web.dto.response.RandomFoodResponse;
import com.team.delightserver.web.dto.response.SurveyFoodResponse;
import com.team.delightserver.web.dto.response.TagRelatedFoodsResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Created by Bloo, Doe
 * @Date: 2021/08/09, 2021/08/11
 * @ModifiedDate : 2021/08/17
 */

@Slf4j
@RequestMapping("/api/foods")
@RequiredArgsConstructor
@RestController
public class ApiFoodController {

    private final ApiFoodService apiFoodService;

    @GetMapping ("/{categoryId}")
    public ResponseEntity<List<SurveyFoodResponse>> findRandomFoodsForSurvey(@PathVariable Long categoryId) {
        return ResponseEntity.ok().body(apiFoodService.findRandomFoodsForSurvey(categoryId));
    }

    @PostMapping("/tags")
    public ResponseEntity<List<TagRelatedFoodsResponse>> findFoodsByTags(@RequestBody FindFoodsByTagsRequest findFoodsByTagsRequest, Pageable pageable) {
        return ResponseEntity.ok().body(apiFoodService.findFoodsByTags(findFoodsByTagsRequest, pageable));
    }

    /**
     * 아래부터 프론트 개선 후 삭제될 로직 입니다.
     */
    @GetMapping ("")
    public ResponseEntity<List<RandomFoodResponse>> findRandomFoodsForSurvey() {
        return ResponseEntity.ok().body(apiFoodService.findRandomFoodsForSurvey());
    }
}
