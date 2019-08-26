package com.example.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class CompletabelFutureTest1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        runAsync();

        supplyAsync();

    }

    /**
     * 无返回值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.runAsync(() -> {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("runAsync end ...");
        });

        future.get();
    }

    /**
     * 有返回值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void supplyAsync() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "supplyAsync end ...";
        });

        System.out.println("get："+future.get() + "--"+Thread.currentThread().getName());

        System.out.println("======="+Thread.currentThread().getName());
    }
}
