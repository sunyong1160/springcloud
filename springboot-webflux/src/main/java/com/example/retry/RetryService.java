package com.example.retry;

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
}
