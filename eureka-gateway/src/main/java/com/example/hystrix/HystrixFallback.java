package com.example.hystrix;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixFallback {

    @RequestMapping("/fallback")
    public String fallback() {
        return "hello hystrixFallback";
    }
}
