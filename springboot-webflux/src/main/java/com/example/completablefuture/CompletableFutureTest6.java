package com.example.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * thenRun
 * 该方法同 thenAccept 方法类似。不同的是上个任务处理完成后，并不会把计算的结果传给 thenRun 方法。
 * 只是处理玩任务后，执行 thenRun 的后续操作。
 */
public class CompletableFutureTest6 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        thenRun();
    }

    public static void thenRun() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(10);
            }
        }).thenRun(() -> { // 上一步的任务的结果不会给到thenRun方法里
            System.out.println("thenRun ....");
        });

        System.out.println(future.get()); // 也不会拿到结果 null
    }
}
