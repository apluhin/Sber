package less12.jmm.home;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadPoolTest {

    ThreadPool threadPoolFixed;
    ThreadPool threadPoolScalable;
    Runnable runnable;
    Runnable runnableException;



    @Before
    public void setUp() throws Exception {
        runnable = () -> {
            try {

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        };
        runnableException = () -> {
            throw new RuntimeException("Some error");
        };
        threadPoolFixed = new FixedThreadPool(3);
        threadPoolScalable = new ScalableThreadPool(3, 6);
    }


    @Test
    public void testException() throws Exception {
        threadPoolFixed.execute(runnableException);
        threadPoolFixed.start();

        threadPoolScalable.execute(runnableException);
        threadPoolScalable.shutdown();
        threadPoolFixed.shutdown();
    }

    @Test
    public void testFixedThreadSPool() throws Exception {

        threadPoolFixed.execute(runnableException);
        threadPoolFixed.execute(runnable);
        threadPoolFixed.execute(runnable);
        threadPoolFixed.start();
        Thread.sleep(3400);
    }

    @Test
    public void testScalablePoolOnTimeOut() throws Exception {

        for (int i = 0; i < 6; i++) {
            threadPoolScalable.execute(runnable);
        }
        threadPoolScalable.start();
        Thread.sleep(5000);
        threadPoolScalable.shutdown();
        assertEquals(threadPoolScalable.isShutdown(), true);
    }
}