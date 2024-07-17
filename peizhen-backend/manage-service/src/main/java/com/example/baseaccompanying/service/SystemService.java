package com.example.baseaccompanying.service;

import org.springframework.web.bind.annotation.RequestParam;

public interface SystemService {
    /**
     * 生成验证码
     * @param username
     * @return
     */
    public String generateVerificationCode(String username);
}
