package less12.jmm.home;

public interface ThreadPool {

    void execute(Runnable runnable);

    void start();

    void shutdown();

    boolean isShutdown();

}


