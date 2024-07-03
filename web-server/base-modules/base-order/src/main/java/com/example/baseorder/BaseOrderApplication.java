package com.example.baseorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.baseorder.mapper"})
@ComponentScan(basePackages = {"huice.accompaniment","com.example"})
public class BaseOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseOrderApplication.class, args);
    }

}
