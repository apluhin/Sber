package less14.home;

import less14.home.service.EqualLockService;
import less14.home.service.Service;
import less14.home.service.SlowService;

public class Main {
    public static void main(String[] args) {
        Service service = new EqualLockService(new SlowService());
        Runnable runnable = () -> service.run(1);
        Runnable runnable1 = () -> service.run(2);


        start(runnable);
        start(runnable1);
        start(runnable);
        start(runnable);
        start(runnable);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        start(runnable1);
        start(runnable);
    }

    private static void start(Runnable runnable) {
        new Thread(runnable).start();
    }


}
