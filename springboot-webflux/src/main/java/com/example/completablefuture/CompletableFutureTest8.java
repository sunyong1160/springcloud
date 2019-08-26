package com.example.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * thenAcceptBoth
 * 当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗
 */
public class CompletableFutureTest8 {

    public static void main(String[] args){

        CompletableFuture future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "hello1";
            }
        });

        CompletableFuture future2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "hello2";
            }
        });

        future1.thenAcceptBoth(future2, new BiConsumer<String, String>() {
            @Override
            public void accept(String s, String s2) {
                System.out.println(s+"=="+s2);
            }
        });

        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
