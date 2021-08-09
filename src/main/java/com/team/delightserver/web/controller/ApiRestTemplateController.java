package com.team.delightserver.web.controller;

import com.team.delightserver.web.dto.request.SelectedFoodRequestDto;
import com.team.delightserver.web.dto.response.RandomFoodsResponse;
import com.team.delightserver.web.dto.response.RecommendedFoodResponseDto;
import com.team.delightserver.web.service.ApiRestTemplateService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CreateBy:Min
 * @Date: 2021/07/27
 */

@Slf4j
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
@RestController
public class ApiRestTemplateController {

    private final ApiRestTemplateService apiRestTemplateService;

    @PostMapping("")
    public ResponseEntity<RecommendedFoodResponseDto> findMlResults(
            @RequestBody SelectedFoodRequestDto selectedFoodRequestDto) {
        return ResponseEntity.ok().body(apiRestTemplateService.getMlResults(selectedFoodRequestDto));
    }

    @GetMapping("")
    public ResponseEntity<List<RandomFoodsResponse>> findRandomFoodsLimit20() {
       return ResponseEntity.ok().body(apiRestTemplateService.findFoodsRandom());
    }
}