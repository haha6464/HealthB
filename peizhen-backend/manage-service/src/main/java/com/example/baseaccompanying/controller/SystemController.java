package com.example.baseaccompanying.controller;

import com.example.baseaccompanying.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    /**
     * 生成验证码
     *
     * @param username
     * @return
     */
    @PostMapping("/GenerateVerificationCode")
    public String generateVerificationCode(@RequestParam("username") String username) {
        return systemService.generateVerificationCode(username);
    }
}
