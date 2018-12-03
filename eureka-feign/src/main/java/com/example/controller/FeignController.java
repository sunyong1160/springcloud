package com.example.controller;

import com.example.hystrix.HystrixFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sunyong on 2018/12/1.
 */
@FeignClient(name = "eureka-hi",fallbackFactory = HystrixFallback.class)
public interface FeignController {

    @RequestMapping("/demo")
    String demo();

}
