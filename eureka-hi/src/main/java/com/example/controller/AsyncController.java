package com.example.controller;

import com.example.async.AsyncTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("async")
public class AsyncController {

    private static final Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private AsyncTask asyncTask;

    /**
     * 异步调用
     * mainThread[http-nio-8762-exec-1,5,main]---2018-12-20T15:07:32.938
     * mainThread[http-nio-8762-exec-1,5,main]---2018-12-20T15:07:32.944
     * sonThread[SimpleAsyncTaskExecutor-1,5,main]
     * do Something
     *
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get() {
        logger.info("main" + Thread.currentThread().getName() + "---" + LocalDateTime.now());
        asyncTask.doSomeThing("do Something");
        logger.info("main" + Thread.currentThread().getName() + "---" + LocalDateTime.now());
        return "success";
    }

    @RequestMapping(value = "/future/{id}", method = RequestMethod.GET)
    public String future(@PathVariable("id") Integer id) {
        try {
            logger.info("main" + Thread.currentThread() + "---" + LocalDateTime.now());
            Future<String> future = asyncTask.asyncInvokeReturnFuture(id);
            // 模拟主线程耗时比异步调用耗时长
            Thread.sleep(8000);
            logger.info("main" + Thread.currentThread() + "---" + LocalDateTime.now());
            // 去拿异步调用的结果，上面主流程已经走完
            String s = future.get();
            logger.info("异步调用返回的结果" + Thread.currentThread() + "****:" + s + "," + LocalDateTime.now());
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

}
