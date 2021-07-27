package com.team.delightserver.web.service;

import com.team.delightserver.web.dto.request.SelectedFoodRequestDto;
import com.team.delightserver.web.dto.response.RecommendedFoodResponseDto;
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
@Service
public class ApiRestTemplateService {

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
}
