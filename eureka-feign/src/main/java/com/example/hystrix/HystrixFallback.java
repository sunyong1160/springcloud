package com.example.hystrix;

import com.example.controller.FeignController;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by sunyong on 2018/12/1.
 */
@Component
public class HystrixFallback implements FallbackFactory {

    private static final Logger logger = LoggerFactory.getLogger(HystrixFallback.class);

    @Override
    public Object create(Throwable throwable) {
        logger.error(throwable.getMessage(), throwable);
        return null;
    }
}
