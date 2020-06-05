package leetcode.multithread.print_zero_even_odd;

import java.util.concurrent.Semaphore;

/**
 * 交替打印奇数和偶数，第一个打印的数是1
 */
public class OddEven {
    private static int n;
    private static Semaphore odd = new Semaphore(1);
    private static Semaphore even = new Semaphore(0);

    public OddEven(int n){
        this.n = n;
    }

    public static void printOdd(){
        for (int i = 1; i <= n; i += 2) {
            try {
                odd.acquire();
                System.out.print(i + " ");
                even.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printEven(){
        for (int i = 2; i <= n; i += 2) {
            try {
                even.acquire();
                System.out.print(i + " ");
                odd.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        OddEven oddEven = new OddEven(15);
        // 两种方式等效
//        new Thread(() -> oddEven.printOdd()).start();
        new Thread(OddEven::printOdd).start();
        new Thread(() -> oddEven.printEven()).start();
    }
}
