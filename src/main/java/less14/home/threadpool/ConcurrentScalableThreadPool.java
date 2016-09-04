package less14.home.threadpool;

import less12.jmm.home.ThreadPool;

import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentScalableThreadPool implements ThreadPool {

    private final BlockingQueue<Runnable> runnables;
    private final Set<Worker> workerSet = new CopyOnWriteArraySet<>();
    private final Runnable poisonPill;
    private final int minThread;
    private final int maxThread;
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private volatile boolean isShutdown = false;

    public ConcurrentScalableThreadPool(int capacity, Runnable poisonPill, int minThread, int maxThread) {
        this.runnables = new ArrayBlockingQueue<>(capacity);
        this.poisonPill = poisonPill;
        this.minThread = minThread;
        this.maxThread = maxThread;
    }

    @Override
    public void execute(Runnable runnable) {
        if (!isFindWaiting() && workerSet.size() < maxThread) {
            startWorker();
        }
        runnables.add(runnable);
    }

    private boolean isFindWaiting() {
        for (Worker worker : workerSet) {
            if (worker.getState() == Thread.State.WAITING) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void start() {
        for (int i = 0; i < minThread; i++) {
            startWorker();
        }
    }

    private void startWorker() {
        Worker worker = new Worker();
        workerSet.add(worker);
        worker.start();
    }

    @Override
    public void shutdown() {
        isShutdown = true;
        for (int i = 0; i < maxThread; i++) {
            runnables.add(poisonPill);
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
                    run = runnables.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (run == poisonPill) {
                    return;
                } else {
                    run.run();
                }
                if (reviewCountThread()) return;

            }
        }

        private boolean reviewCountThread() {
            reentrantLock.lock();
            try {
                if (workerSet.size() > minThread && runnables.isEmpty()) {
                    workerSet.remove(this);
                    return true;
                } else {
                    return false;
                }
            } finally {
                reentrantLock.unlock();
            }
        }
    }

}
