package com.example.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "eureka-hi2")
public interface Hi2FeginController {

    @RequestMapping("/hi2/demo2")
    public String demo2hi2();
}
