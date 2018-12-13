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
public class HystrixFallback implements FallbackFactory<FeignController> {

    private static final Logger logger = LoggerFactory.getLogger(HystrixFallback.class);


    @Override
    public FeignController create(Throwable cause) {

        return new FeignController() {
            @Override
            public String demo() {
                logger.info("demo进入断路器：{}", cause);
                return "调用失败，进入熔断器";
            }

            @Override
            public String send() {
                logger.info("send进入断路器：{}", cause);
                return "调用失败，进入熔断器";
            }
        };
    }
}
