package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class EurekaHi2Application {
    public static void main(String[] args) {
        SpringApplication.run(EurekaHi2Application.class, args);
    }

    @RequestMapping("/hi2/demo2")
    public String demohi2() {


        return "hi2demo2";
    }

}
