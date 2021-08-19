package com.team.delightserver.web.controller;

import com.team.delightserver.security.annotation.CurrentUser;
import com.team.delightserver.security.oauth2.ProviderOAuth2User;
import com.team.delightserver.service.ApiMypickService;
import com.team.delightserver.web.dto.response.MypickResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @CreateBy:Min
 * @Date: 2021/08/18
 */

@Slf4j
@RequestMapping("/api/users/histories")
@RequiredArgsConstructor
@RestController
public class ApiMypickController {

    private final ApiMypickService apiMypickService;

    @GetMapping("")
    public ResponseEntity<List<MypickResponse>> findAllMypick(
            @CurrentUser ProviderOAuth2User user) {
        return ResponseEntity.ok().body(
                apiMypickService.findMypickByUserId(user.getId()));
    }
}
