package less12.jmm.home;

import java.util.*;

public class ScalableThreadPool implements ThreadPool {

    private final int minCountThread;
    private final int maxCountThread;


    private final Queue<Runnable> tasks = new ArrayDeque<>();
    private final Object lock = new Object();
    private final Object lockOnList = new Object();
    private boolean isStop = false;
    private boolean isStart = false;
    private final List<Worker> workerList = new ArrayList<>();

    ScalableThreadPool(int minCountThread, int maxCountThread) {
        this.minCountThread = minCountThread;
        this.maxCountThread = maxCountThread;
    }


    @Override
    public void execute(Runnable runnable) {
        checkStart();
        tasks.add(runnable);
        synchronized (lockOnList) {
            if (!isFreeThread()) {
                if (workerList.size() < maxCountThread) {
                    startWorker();
                }
            }
        }
        synchronized (lock) {
            lock.notify();
        }
    }

    private void checkStart() {
        if (!isStart) {
            isStart = true;
            start();
        }
    }

    @Override
    public void start() {
        for (int i = 0; i < minCountThread; i++) {
            startWorker();
        }
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    private void startWorker() {
        Worker worker = new Worker(this);
        workerList.add(worker);
        worker.start();
    }

    @Override
    public void shutdown() {
        isStop = true;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    @Override
    public boolean isShutdown() {
        return isStop;
    }


    private boolean isFreeThread() {
        for (Worker worker : workerList) {
            if (worker.getState().name().equals("WAITING")) {
                return true;
            }
        }
        return false;
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
                            if (checkWorker()) return;
                            lock.wait();
                            if (isShutdown()) return;
                        } catch (InterruptedException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    current = tasks.poll();
                }
                try {
                    current.run();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                synchronized (lock) {
                    if (reviewThread()) return;
                }
            }
        }

        private boolean reviewThread() {
            return checkWorker() || isShutdown();
        }

        private boolean checkWorker() {
            synchronized (lockOnList) {
                if (tasks.isEmpty()) {
                    if (workerList.size() > minCountThread) {
                        workerList.remove(this);
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean isShutdown() {
            return threadPool.isShutdown();
        }

    }

}
