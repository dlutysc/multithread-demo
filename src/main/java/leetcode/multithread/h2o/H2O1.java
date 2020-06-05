package leetcode.multithread.h2o;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class H2O1 {
    private Semaphore semaphoreH = new Semaphore(2);
    private Semaphore semaphoreO = new Semaphore(1);
    private AtomicInteger ai = new AtomicInteger(0);

    public H2O1() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        semaphoreH.acquire(1);
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        ai.incrementAndGet();
        reSet();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        semaphoreO.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        ai.incrementAndGet();
        reSet();
    }

    private void reSet(){
        if (ai.compareAndSet(3, 0)){
            semaphoreH.release(2);
            semaphoreO.release(1);
        }
    }

    public static void main(String[] args) {
        H2O1 h2O = new H2O1();
        for (int i = 0; i < 6; ++i) {
            new Thread(() -> {
                try {
                    h2O.hydrogen(() -> System.out.println("H"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    h2O.oxygen(() -> System.out.println("O"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
