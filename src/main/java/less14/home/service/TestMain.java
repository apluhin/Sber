package less14.home.service;


public class TestMain {
    public static void main(String[] args) throws InterruptedException {

        Service service = new EqualLockService(new SlowService());
        Runnable runnable = () -> service.run(1);
        Runnable runnable1 = () -> service.run(2);
        Runnable runnable2 = () -> service.run(3);


        start(runnable1);
        start(runnable1);
        start(runnable2);
        Thread.sleep(5000);
        System.out.println("www");
        start(runnable2);
        start(runnable2);
        start(runnable);
        start(runnable);
        start(runnable);
        start(runnable2);
        start(runnable2);


        start(runnable1);
        Thread.sleep(5000);
        start(runnable1);
        start(runnable1);
        start(runnable1);
        start(runnable1);
        start(runnable1);









    }

    private static void start(Runnable runnable) {
        new Thread(runnable).start();
    }


}
