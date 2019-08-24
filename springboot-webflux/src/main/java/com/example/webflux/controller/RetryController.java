package com.example.webflux.controller;

import com.example.retry.DemoService;
import com.example.retry.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetryController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/retry")
    public String retry(){
        String res = demoService.retry();
        return "success -- " + res;
    }

    @RequestMapping("/spring/retry")
    public String springRetry(){
        return demoService.springRetry() + "===";
    }
}
