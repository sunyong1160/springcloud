package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@SpringBootApplication
@RestController
@EnableRetry // 使spring @Retryable 生效
public class WebFluxApplication {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);
    }

    @RequestMapping("/get/demo")
    @ResponseBody
    public String get() {
        String kkk = redisTemplate.opsForValue().get("kkk");
        return kkk;
    }
}
