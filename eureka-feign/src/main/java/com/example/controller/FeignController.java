package com.example.controller;

import com.example.hystrix.HystrixFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sunyong on 2018/12/1.
 */
@FeignClient(name = "qwe",url = "http://localhost:8762",fallback = HystrixFallback.class)
public interface FeignController {

    @RequestMapping("/demo")
    String demo();

}
