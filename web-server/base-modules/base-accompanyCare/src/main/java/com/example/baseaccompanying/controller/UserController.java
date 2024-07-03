package com.example.baseaccompanying.controller;

import huice.accompaniment.common.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/tmpToken")
    public String tmpToken(){
        return "";
    }
}
