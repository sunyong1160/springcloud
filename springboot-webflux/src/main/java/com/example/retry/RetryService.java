package com.example.retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RetryService {

    private AtomicInteger atomicInteger = new AtomicInteger();

    public String getRetry(){

        int num = atomicInteger.getAndIncrement();
        if(num < 3){
            System.out.println(num+":=====");
            int i = 1 / 0;
            return "123";
        }
        System.out.println("22222");
        return "success123";
    }


    // 最大重试三次，第一次重试时间间隔100ms，第二次重试时间间隔200ms 第三次重试时间间隔400ms
    @Retryable(value = RuntimeException.class, backoff = @Backoff(value = 100, multiplier = 2)) // 默认重试3次
    public String getSpringRetry(){

        int num = atomicInteger.incrementAndGet();
        if(num < 3){

            System.out.println(num+"==="+System.currentTimeMillis());
            int i = 1 / 0;

        }

        System.out.println("3333");
        return "success";
    }
}
