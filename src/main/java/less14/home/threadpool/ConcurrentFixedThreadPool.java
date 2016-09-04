package less14.home.threadpool;

import less12.jmm.home.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ConcurrentFixedThreadPool implements ThreadPool {

    private final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1000);
    private final Runnable poisonPill;
    private final int count;
    private volatile boolean isShutdown = false;

    public ConcurrentFixedThreadPool(Runnable poisonPill, int count) {
        this.poisonPill = poisonPill;
        this.count = count;
    }

    @Override
    public void execute(Runnable runnable) {
        queue.add(runnable);
    }

    @Override
    public void start() {
        for (int i = 0; i < count; i++) {
            new Worker().start();
        }
    }

    @Override
    public void shutdown() {
        isShutdown = true;
        for (int i = 0; i < count; i++) {
            queue.add(poisonPill);
        }
    }

    @Override
    public boolean isShutdown() {
        return isShutdown;
    }

    private class Worker extends Thread {
        @Override
        public void run() {
            while (!isShutdown) {
                Runnable run = null;
                try {
                    run = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (run == poisonPill) {
                    return;
                } else {
                    run.run();

                }
            }
        }
    }

}
