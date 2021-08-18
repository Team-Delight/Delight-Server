package com.team.delightserver.web.controller;

import com.team.delightserver.security.annotation.CurrentUser;
import com.team.delightserver.security.oauth2.ProviderOAuth2User;
import com.team.delightserver.service.MypickService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Created by Doe
 * @Date: 2021/08/18
 */

@RequestMapping("/api/mypicks")
@RequiredArgsConstructor
@RestController
public class ApiMypickController {

    private final MypickService mypickService;

    @PostMapping("")
    public ResponseEntity<Void> saveMypick(@CurrentUser ProviderOAuth2User user, @RequestParam Long foodId) {
        mypickService.saveMypick(user, foodId);
        return ResponseEntity.ok().body(null);
    }
}
