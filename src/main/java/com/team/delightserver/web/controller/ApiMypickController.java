package com.team.delightserver.web.controller;

import com.team.delightserver.security.annotation.CurrentUser;
import com.team.delightserver.security.oauth2.OAuth2UserProvider;
import com.team.delightserver.service.MypickService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Created by Doe
 * @Date: 2021/08/18
 * @ModifiedDate: 2021/08/19
 */

@RequestMapping("/api/mypicks")
@RequiredArgsConstructor
@RestController
public class ApiMypickController {

    private final MypickService mypickService;

    @PostMapping("")
    public ResponseEntity<Void> saveMypick(@CurrentUser OAuth2UserProvider user, @RequestParam Long foodId) {
        mypickService.saveMypick(user, foodId);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
