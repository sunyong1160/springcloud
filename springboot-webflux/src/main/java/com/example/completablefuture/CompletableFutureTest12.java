package com.example.completablefuture;


import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * runAfterBoth
 *
 * 两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
 * public CompletionStage<Void> runAfterBoth(CompletionStage<?> other,Runnable action);
 * public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action);
 * public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action,Executor executor);
 */
public class CompletableFutureTest12 {

    public static void main(String[] args) throws InterruptedException {

        runAfterBoth();

        TimeUnit.SECONDS.sleep(3);
    }

    public static void runAfterBoth(){
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f1="+t);
                return t;
            }
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f2="+t);
                return t;
            }
        });

        f1.runAfterBoth(f2, new Runnable() {

            @Override
            public void run() {
                System.out.println("上面两个任务都执行完成了。");
            }
        });

    }
}
