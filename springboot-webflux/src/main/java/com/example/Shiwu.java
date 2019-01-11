package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunyong on 2019/1/7.
 */
@Component
public class Shiwu {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * spring容器启动后加载数据
     */
    @PostConstruct
    public void init() {
        String str = "1234567890";
        redisTemplate.opsForValue().set("kkk", str);
    }
}
