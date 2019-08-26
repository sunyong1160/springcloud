package com.example.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 计算结果完成时的回调方法
 * public CompletableFuture<T> whenComplete(BiConsumer<? super T,? super Throwable> action)
 * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
 * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
 * public CompletableFuture<T> exceptionally(Function<Throwable,? extends T> fn)
 */
@SuppressWarnings("Duplicates")
public class CompletableFutureTest2 {


    /**
     *
     * 可以看到Action的类型是BiConsumer<? super T,? super Throwable>它可以处理正常的计算结果，或者异常情况。
     * whenComplete 和 whenCompleteAsync 的区别：
     * whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。
     * whenCompleteAsync：是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行。
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        whenComplete();

    }

    public static void whenComplete() throws InterruptedException {
        CompletableFuture future = CompletableFuture.runAsync(()->{

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("runAsync end ...");
        });

        future.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void aVoid, Throwable throwable) {
                System.out.println("执行完成 。。。");
            }
        });

        future.exceptionally(new Function<Throwable, Void>() {

            @Override
            public Void apply(Throwable throwable) {
                System.out.println("执行失败 。。。");
                return null;
            }
        });

        TimeUnit.SECONDS.sleep(3);
    }

}
