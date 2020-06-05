package concurrency_art.content8_2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest2 {
    private static CyclicBarrier barrier = new CyclicBarrier(2, new A());
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }).start();

        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }

    static class A implements Runnable{
        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
