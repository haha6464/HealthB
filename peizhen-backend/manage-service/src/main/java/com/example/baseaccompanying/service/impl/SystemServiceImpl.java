package com.example.baseaccompanying.service.impl;

import com.example.baseaccompanying.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService {

    /**
     * 生成验证码
     * @param username
     * @return
     */
    @Override
    public String generateVerificationCode(String username) {

        return null;
    }
}
