package leetcode.multithread.print_zero_even_odd;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TestSemaphor {
    private static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args){
        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("开始访问");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("释放许可");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
