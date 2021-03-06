package com.team.delightserver.web.controller;

import com.team.delightserver.security.annotation.CurrentUser;
import com.team.delightserver.security.oauth2.OAuth2UserProvider;
import com.team.delightserver.service.ApiTagService;
import com.team.delightserver.util.enumclass.TagType;
import com.team.delightserver.web.dto.response.TagResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by Doe
 * @Date: 2021/08/13
 * @ModifiedDate: 2021/08/19
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

    @GetMapping(value = "", params = {"type"})
    public ResponseEntity<List<TagResponse>> findAllTagsByType(@RequestParam TagType type) {
        return ResponseEntity.ok().body(apiTagService.findAllTagsByType(type));
    }

    @GetMapping("/users/frequent-tag")
    public ResponseEntity<TagResponse> findMostFrequentTag(
            @CurrentUser OAuth2UserProvider user, @RequestParam(defaultValue = "COUNTRY") TagType type) {
        return ResponseEntity.ok().body(
                apiTagService.findMostFrequentTagByUserId(user.getId(), type));
    }
}