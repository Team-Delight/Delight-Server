package com.team.delightserver.web.controller;

import com.team.delightserver.service.ApiFoodService;
import com.team.delightserver.web.dto.response.RandomFoodsResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by Bloo
 * @Date: 2021/08/09
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
}
