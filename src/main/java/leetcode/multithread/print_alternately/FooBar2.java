package leetcode.multithread.print_alternately;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 使用CountDownLatch，CyclicBarrier
 */
public class FooBar2 {
    private int n;
    private CountDownLatch latch;
    private CyclicBarrier barrier;

    public FooBar2(int n){
        this.n = n;
        latch = new CountDownLatch(1);
        barrier = new CyclicBarrier(2); //保证每组内有两个任务
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            try {
                printFoo.run();
                latch.countDown();
                barrier.await(); //等待printBar方法执行完成
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            try {
                latch.await(); //等待printFoo方法先执行
                printBar.run();
                latch = new CountDownLatch(1);
                barrier.await(); //等待printFoo方法执行完成
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        final Runnable printFoo = new PrintFoo();
        final Runnable printBar = new PrintBar();
        final FooBar2 fooBar2 = new FooBar2(3);

        new Thread(() -> {
            try {
                fooBar2.foo(printFoo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fooBar2.bar(printBar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
