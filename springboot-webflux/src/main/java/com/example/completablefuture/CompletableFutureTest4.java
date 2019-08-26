package com.example.completablefuture;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * handle
 * handle 是执行任务完成时对结果的处理。
 * handle 方法和 thenApply 方法处理方式基本一样。不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。
 * thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法
 * public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
 * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
 * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn,Executor executor);
 *
 */
public class CompletableFutureTest4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        handle();

    }

    public static void handle() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                // 模拟异常情况
                int i = 10 / 0;
                int num = new Random().nextInt(10);
                System.out.println(num+"====");
                return num;
            }
        }).handle(new BiFunction<Integer, Throwable, Object>() {
            @Override
            public Object apply(Integer param, Throwable throwable) {
                int result = -1;
                if(throwable == null){
                    result = param * 2;
                } else {
                    System.out.println(throwable.getMessage()+"----");
                }

                return result;
            }
        });
        System.out.println(future.get());
    }

}
