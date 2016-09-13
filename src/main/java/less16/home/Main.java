package less16.home;

public class Main {
    private static Boolean PING = true;
    private static Object lock = new Object();

    public static void main(String[] args) {
        Runnable runnable = () -> {
            synchronized (lock) {
                while (true) {
                    if (PING) {
                        System.out.println("PING");
                        PING = false;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        Runnable runnable1 = () -> {
            synchronized (lock) {
                while (true) {
                    if (!PING) {
                        System.out.println("PONG");
                        PING = true;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        new Thread(runnable).start();
        new Thread(runnable1).start();
    }


}
