package com.team.delightserver.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by Bloo
 * @Date: 2021/07/27
 *
 * Description
 * - 배포시 핑 테스트를 위한 클래스 입니다
 */
@Slf4j
@RestController
public class ApiHelloController {

    @GetMapping("/api/hello")
    public String hello () {
        log.info("Start Ping Test Log");
        return "Hello";
    }

    @GetMapping("/api/error")
    public String error () {
        log.error("Error Log Test");
        return "ERROR";
    }
}

