package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Created by sunyong on 2018/12/1.
 */
@RestController
public class DemoController {

    @Autowired
    private FeignController feignController;

    @Autowired
    private Hi2FeginController hi2FeginController;

    @RequestMapping("/demo")
    public String demo() {
//        int i = 1/0;
        System.out.println(1234);
        return feignController.demo();
    }

    @RequestMapping("/demo/hi2/demo2")
    public String demo2() {
//        int i = 1/0;
        return hi2FeginController.demo2hi2();
    }
}
