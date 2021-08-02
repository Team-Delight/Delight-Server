package com.team.delightserver.web.service;

import com.team.delightserver.web.domain.food.RecommendationRepository;
import com.team.delightserver.web.dto.request.SelectedFoodRequestDto;
import com.team.delightserver.web.dto.response.RecommendedFoodResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @CreateBy:Min
 * @Date: 2021/07/27
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiRestTemplateService {

    private final RecommendationRepository recommendationRepository;

    public RecommendedFoodResponseDto getMlResults(SelectedFoodRequestDto selectedFoodRequestDto) {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/Ml-servers")
                .encode()
                .build()
                .toUri();

        log.info("URI : {}", uri);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<RecommendedFoodResponseDto> responseEntity = restTemplate.postForEntity(
                uri,
                selectedFoodRequestDto,
                RecommendedFoodResponseDto.class
        );

        log.info("StatusCode : {}", responseEntity.getStatusCode());
        log.info("Headers info : {}", responseEntity.getHeaders());
        log.info("Response Body : {}", responseEntity.getBody());

        // 1차 로직작성
        // recommendationRepository을 이용해서 저장
        // 이때 시간대가 오늘이고 recommendation에 있는 경우 count +1
        // 이때 시간대가 오늘이고 recommendation에 없는 경우 add, count +1
        // 어제 시간대인 경우 고려하지 않아도 됨

        return responseEntity.getBody();
    }
}
