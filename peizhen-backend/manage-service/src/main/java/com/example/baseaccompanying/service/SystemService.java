package com.example.baseaccompanying.service;

public interface SystemService {
    /**
     * 生成验证码
     *
     * @param username
     * @return
     */
    public String generateVerificationCode(String username);
}
