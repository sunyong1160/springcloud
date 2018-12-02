package com.example.service.impl;

import com.example.service.DemoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by sunyong on 2018/12/1.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Value("${server.port}")
    private int port;

    @Override
    public String demo() {
        return "demo port：" + port;
    }
}
