package concurrency.thread;

import java.util.concurrent.Callable;

public class ThreadC implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(500L);
        System.out.println("这是线程C");
        return "线程C";
    }
}
