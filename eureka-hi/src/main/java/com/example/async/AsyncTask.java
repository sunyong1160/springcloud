package com.example.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * 异步的方法有3种
 * 1. 最简单的异步调用，返回值为void
 * 2. 带参数的异步调用 异步方法可以传入参数
 * 3. 异常调用返回Future
 */
@Component
public class AsyncTask {

    private static final Logger logger = LoggerFactory.getLogger(AsyncTask.class);

    @Async
    public void doSomeThing(String s) {
        try {
            Thread.sleep(3000);
            logger.info("son" + Thread.currentThread().getName());
            //doSomeThing
            logger.info(s);
        } catch (InterruptedException e) {
        }
    }


    /**
     * 最简单的异步调用，返回值为void
     */
    @Async
    public void asyncInvokeSimplest() {
        logger.info("asyncSimplest");
    }

    /**
     * 带参数的异步调用，异步方法可以传入参数
     *
     * @param s
     */
    @Async
    public void asyncInvokeWithParameter(String s) {
        logger.info("asyncInvokeWithParameter, parementer={}", s);
    }

    /**
     * 异常调用返回Future
     * 对于返回值是Future，不会被AsyncUncaughtExceptionHandler处理，需要我们在方法中捕获异常并处理
     * 或者在调用方在调用Futrue.get时捕获异常进行处理
     *
     * @param i
     * @return
     */
    @Async
    public Future<String> asyncInvokeReturnFuture(int i) {
        logger.info("asyncInvokeReturnFuture, parementer={}", i);
        Future<String> future;
        try {
            Thread.sleep(5000);
            future = new AsyncResult<String>("success:" + i);
        } catch (InterruptedException e) {
            future = new AsyncResult<>("error");
        }
        return future;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<String> task = () -> {
            //模拟计算时间
            Thread.sleep(8000);
            return "Callable接口实现的返回结果";
        };
        //创建FutureTask来包装Callable接口实现
        FutureTask<String> futureTask = new FutureTask<>(task);
        //创建线程池准备执行任务
        ExecutorService service = Executors.newFixedThreadPool(1);
        //执行任务，线程池将会分配一个线程去执行指定的任务
        service.execute(futureTask);
        //主线程执行其它任务
        Thread.sleep(2000);
        System.out.println("主线程执行其它任务花费了2秒..." + LocalDateTime.now());
        //主线程需要子线程任务的结果
        String result = futureTask.get();
        System.out.println("FutureTask任务的执行结果是:" + result + "...." + LocalDateTime.now());
        //关闭线程池
        service.shutdown();
    }


}
