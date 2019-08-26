package com.example.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * thenAccept
 * 处理消费结果，接收任务的处理结果，并消费处理，无返回结果
 * public CompletionStage<Void> thenAccept(Consumer<? super T> action);
 * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
 * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor);
 */
public class CompletableFutureTest5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenAccept();
    }

    public static void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(10);
            }
        }).thenAccept(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer +" : ===");
            }
        });

        System.out.println(future.get());// null 无返回结果
    }
}
