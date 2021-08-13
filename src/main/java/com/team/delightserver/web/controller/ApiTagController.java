package com.team.delightserver.web.controller;

import com.team.delightserver.service.ApiTagService;
import com.team.delightserver.web.dto.response.TagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Created by Doe
 * @Date: 2021/08/13
 */

@RequestMapping("/api/tags")
@RequiredArgsConstructor
@RestController
public class ApiTagController {
    private final ApiTagService apiTagService;

    @GetMapping("")
    public ResponseEntity<List<TagResponse>> findAllTags() {
        return ResponseEntity.ok().body(apiTagService.findAllTags());
    }
}