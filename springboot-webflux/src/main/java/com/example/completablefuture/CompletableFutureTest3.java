package com.example.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * thenApply
 * 当一个线程依赖另一个线程时，使用thenApply方法把两个线程串行化
 * 只能执行任务正常的情况下，当任务失败的时候不会继续执行thenApply
 */
public class CompletableFutureTest3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenApply();
    }

    public static void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "result";
            }
        }).thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {
                String ss = s + " :thenApply";
                return ss;
            }
        });

        String s = future.get();
        System.out.println(s);
    }
}
