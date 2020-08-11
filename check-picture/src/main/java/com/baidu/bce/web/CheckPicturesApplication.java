package com.baidu.bce.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Author: yxw
 * Date: Create in 2020/7/1
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.baidu.bce.web")
public class CheckPicturesApplication {
    public static void main(String[] args) {
        SpringApplication.run(CheckPicturesApplication.class, args);
    }

}
