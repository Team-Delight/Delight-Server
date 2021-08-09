package com.team.delightserver.web.service;

import com.team.delightserver.web.domain.food.FoodRepository;
import com.team.delightserver.web.dto.request.SelectedFoodRequestDto;
import com.team.delightserver.web.dto.response.RandomFoodsResponse;
import com.team.delightserver.web.dto.response.RecommendedFoodResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    private final FoodRepository foodRepository;

    /**
     * 코드 리펙토링이 필요 Util 로 ServerToServer 이관
     */
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

            return responseEntity.getBody();
        }

        @Transactional(readOnly = true)
        public List<RandomFoodsResponse> findFoodsRandom () {
            return foodRepository.findAllRandom()
                .stream()
                .map(RandomFoodsResponse::of)
                .collect(Collectors.toList());
        }
}
