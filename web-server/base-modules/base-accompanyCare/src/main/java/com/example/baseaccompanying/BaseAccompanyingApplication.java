package com.example.baseaccompanying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 惠策集团
 * @since 2024-06-03
 */
@SpringBootApplication
@ComponentScan({"huice.accompaniment","com.example"})
public class BaseAccompanyingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseAccompanyingApplication.class, args);
    }

}