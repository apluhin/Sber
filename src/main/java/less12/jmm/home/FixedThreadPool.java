package less12.jmm.home;

import java.util.ArrayDeque;
import java.util.Queue;

public class FixedThreadPool implements ThreadPool {
    private final int countThread;
    private final Queue<Runnable> tasks = new ArrayDeque<>();
    private final Object lock = new Object();
    private volatile boolean stop = false;


    public FixedThreadPool(int countThread) {
        this.countThread = countThread;
    }

    @Override
    public void execute(Runnable runnable) {
        tasks.add(runnable);
        synchronized (lock) {
            lock.notify();
        }
    }

    public void start() {
        //start all
        for (int i = 0; i < countThread; i++) {
            new Worker(this).start();
        }
        synchronized (lock) {
            lock.notifyAll();
        }

    }

    @Override
    public boolean isShutdown() {
        return stop;
    }


    @Override
    public void shutdown() {
        stop = true;
        synchronized (lock) {
            lock.notifyAll();
        }
    }


    private class Worker extends Thread {
        private final ThreadPool threadPool;
        private Runnable current;

        Worker(ThreadPool threadPool) {
            this.threadPool = threadPool;
        }

        @Override
        public void run() {
            while (!isShutdown()) {
                synchronized (lock) {
                    while (tasks.isEmpty()) {
                        try {
                            lock.wait();
                            if(isShutdown()) return;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    current = tasks.poll();
                }
                try {
                    current.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        private boolean isShutdown() {
            return threadPool.isShutdown();
        }

    }
}
