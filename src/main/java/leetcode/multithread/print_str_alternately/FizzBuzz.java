package leetcode.multithread.print_str_alternately;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class FizzBuzz {
    private int n;
    private Semaphore fizz = new Semaphore(0);
    private Semaphore buzz = new Semaphore(0);
    private Semaphore fizzBuzz = new Semaphore(0);
    private Semaphore num = new Semaphore(1);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            /**
             * 必须加上这个判断，要不然如果i是15的话fizz.acquire()阻塞，而在num中不会调用fizz.release()
             * 这样线程fizz和buzz将会一直阻塞下去，
             */
            if (i % 5 != 0){
                fizz.acquire();
                printFizz.run();
                num.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0){
                buzz.acquire();
                printBuzz.run();
                num.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            fizzBuzz.acquire();
            printFizzBuzz.run();
            num.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            num.acquire();
            if (i % 3 == 0 && i % 5 == 0) {
                fizzBuzz.release();
            } else if (i % 3 == 0) {
                fizz.release();
            } else if (i % 5 == 0) {
                buzz.release();
            } else {
                printNumber.accept(i);
                num.release();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        FizzBuzz fb = new FizzBuzz(18);

        new Thread(() -> {
            try {
                fb.number(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "num").start();

        new Thread(() -> {
            try {
                fb.fizz(() -> System.out.println("fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "fizz").start();

        new Thread(() -> {
            try {
                fb.buzz(() -> System.out.println("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "buzz").start();

        new Thread(() -> {
            try {
                fb.fizzbuzz(() -> System.out.println("fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "fizzbuzz").start();
    }
}
