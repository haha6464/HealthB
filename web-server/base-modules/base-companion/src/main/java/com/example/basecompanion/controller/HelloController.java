package com.example.basecompanion.controller;

import huice.accompaniment.common.anno.apiAuth.WhiteApi;
import huice.accompaniment.common.utils.ThreadLocalUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 陪诊师
 */
@RestController
@RequestMapping("/hell1o")
public class HelloController {
    /**
     * hello
     * @return
     */

    @GetMapping("hello")
    public String hello(){
        Long uid = ThreadLocalUtils.getUid();
        System.out.println(uid);
        return "hello";
    }
}
