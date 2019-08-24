package com.example.retry;

import com.github.rholder.retry.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class DemoService {

    @Autowired
    private RetryService retryService;

    public String retry(){

        String result = "";

        Retryer<String> retryer = RetryerBuilder.<String>newBuilder()
                .retryIfRuntimeException()
                .retryIfResult(StringUtils::isEmpty)
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))//等待一秒
                .withStopStrategy(StopStrategies.stopAfterAttempt(4))//尝试四次
                .build();

        try {
            result = retryer.call(() -> retryService.getRetry());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RetryException e) {
            e.printStackTrace();
        }
        return result;
    }
}
