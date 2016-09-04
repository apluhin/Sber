package less14.home.threadpool;

import less12.jmm.home.ThreadPool;

import java.util.concurrent.atomic.AtomicInteger;

public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        startScalable();
        startFixed();


    }

    private static void startFixed() throws InterruptedException {
        Runnable posion = () -> {
        };
        ThreadPool threadPool = new ConcurrentFixedThreadPool(posion, 10);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Runnable runnable = () -> {
            try {
                Thread.sleep(100);
                System.out.println(atomicInteger.incrementAndGet());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        threadPool.start();
        for (int i = 0; i < 20; i++) {
            threadPool.execute(runnable);
        }

        Thread.sleep(2500);
        System.out.println("shut");
        threadPool.shutdown();
    }

    private static void startScalable() throws InterruptedException {
        Runnable posion = () -> {
        };
        ThreadPool threadPool = new ConcurrentScalableThreadPool(1000, posion, 10, 20);

        Runnable run = () -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        threadPool.start();
        for (int i = 0; i < 20; i++) {
            threadPool.execute(run);
        }

        Thread.sleep(2500);
        System.out.println("shut");
        threadPool.shutdown();
    }
}
