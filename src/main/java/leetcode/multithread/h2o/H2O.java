package leetcode.multithread.h2o;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class H2O {
    private Semaphore semaphoreH = new Semaphore(2);
    private Semaphore semaphoreO = new Semaphore(1);
    private CyclicBarrier barrier = new CyclicBarrier(3, () -> {
        semaphoreH.release(2);
        semaphoreO.release(1);
    });

    public H2O() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        semaphoreH.acquire(1);
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        semaphoreO.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        H2O h2O = new H2O();
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

//    static class Hydrogen implements Runnable{
//        @Override
//        public void run() {
//            System.out.println("H");
//        }
//    }
//
//    static class Oxygen implements Runnable{
//        @Override
//        public void run() {
//            System.out.println("O");
//        }
//    }
}
