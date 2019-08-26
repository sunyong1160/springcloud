package com.example.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * applyToEither
 *
 * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作
 *
 * public <U> CompletionStage<U> applyToEither(CompletionStage<? extends T> other,Function<? super T, U> fn);
 * public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn);
 * public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn,Executor executor);
 */
public class CompletableFutureTest9 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        applyToEither();
    }

    public static void applyToEither() throws ExecutionException, InterruptedException {

        CompletableFuture future1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int num = new Random().nextInt(3);
                System.out.println("future1:"+num);
                try {
                    TimeUnit.SECONDS.sleep(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return num;
            }
        });

        CompletableFuture future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {

                int num = new Random().nextInt(4);
                System.out.println("future2:"+num);
                try {
                    TimeUnit.SECONDS.sleep(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return num;
            }
        });

        //谁先执行完就拿谁的结果
        CompletableFuture future = future1.applyToEither(future2, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer;
            }
        });

        System.out.println(future.get());

        TimeUnit.MINUTES.sleep(1);
    }
}
