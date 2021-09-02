package com.team.delightserver.web.controller;

import com.team.delightserver.security.annotation.CurrentUser;
import com.team.delightserver.security.oauth2.OAuth2UserProvider;
import com.team.delightserver.service.ApiMypickService;
import com.team.delightserver.web.dto.request.SaveMypickRequest;
import com.team.delightserver.web.dto.response.MypickResponse;
import java.net.URI;
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
 * @CreateBy:Min, Doe
 * @Date: 2021/08/18
 * @ModifiedDate: 2021/08/21
 */

@Slf4j
@RequestMapping("/api/mypicks")
@RequiredArgsConstructor
@RestController

public class ApiMypickController {

    private final ApiMypickService apiMypickService;

    @PostMapping("")
    public ResponseEntity<Void> saveMypick(@CurrentUser OAuth2UserProvider user,
                                           @RequestBody SaveMypickRequest saveMypickRequest) {
        apiMypickService.saveMypick(user, saveMypickRequest);
        return ResponseEntity.created(URI.create("/api/mypicks")).build();
    }

    @GetMapping("/histories")
    public ResponseEntity<List<MypickResponse>> findAllMypick(
            @CurrentUser OAuth2UserProvider user) {
        return ResponseEntity.ok().body(
                apiMypickService.findMypickByUserId(user.getId()));
    }
}