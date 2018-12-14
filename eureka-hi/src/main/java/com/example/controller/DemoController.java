package com.example.controller;

import com.example.mq.Sender;
import com.example.service.DemoService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by sunyong on 2018/12/1.
 */
@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private Sender sender;

    @RequestMapping("/send")
    @ResponseBody
    public String send(){
        sender.send();
        return "success";
    }


    @RequestMapping("/demo")
    @ResponseBody
    public String demo() {
        // 测试网关超时，暂时注释
//        try {
//            Thread.sleep(12000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return demoService.demo();
    }

    @RequestMapping("/count")
    @ResponseBody
    public String countService() {
        List<String> services = discoveryClient.getServices();
        services.forEach(s -> {
            List<ServiceInstance> instances = discoveryClient.getInstances(s);
            System.out.println("服务名称：" + s + "服务数量：" + instances.size());
        });
        return "success";
    }
}
