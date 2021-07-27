package com.team.delightserver.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by Bloo
 * @Date: 2021/07/27
 */

@RestController
public class ApiHelloController {

    @GetMapping("/api/hello")
    public String hello () {
        return "Hello";
    }
}
