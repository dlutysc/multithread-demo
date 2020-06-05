package concurrency.thread3_11;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程安全，性能又高，这种写法也最为常见
 */
public class Singleton4 {
    private static Singleton4 instance;
    private static ReentrantLock lock = new ReentrantLock();
    private Singleton4(){}

    public static Singleton4 getInstance(){
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new Singleton4();
            }
            lock.unlock();
        }
        return instance;
    }
}
