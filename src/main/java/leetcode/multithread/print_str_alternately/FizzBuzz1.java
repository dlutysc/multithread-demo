package leetcode.multithread.print_str_alternately;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * 分析下面的方法不可行的原因，原因在代码中的注释里
 */
public class FizzBuzz1 {
    private int n;
    private Semaphore fizz = new Semaphore(0);
    private Semaphore buzz = new Semaphore(0);
    private Semaphore fizzBuzz = new Semaphore(0);
    private Semaphore num = new Semaphore(1);

    public FizzBuzz1(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            /**
             * 必须加上这个判断，要不然如果i是15的话fizz.acquire()阻塞，而在num中不会调用fizz.release()
             * 这样线程fizz和buzz将会一直阻塞下去
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
                fb.buzz(() -> System.out.println("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "buzz").start();
        /**
         * 当fizz()方法中i循环到18的时候，fizz.acquire()阻塞
         * 此时阻塞的是main线程，
         * 在number()方法中i循环到15的时候，执行fizzBuzz.release()
         * 但是由于main线程阻塞， fb.fizzbuzz(new PrintFizzBuzz()) 无法执行
         * 所以 printFizzBuzz.run()和 num.release() 无法执行
         * 线程num在i = 16时，在num.acquire()处阻塞
         * 代码卡死
         */
        fb.fizz(new PrintFizz());
        fb.fizzbuzz(new PrintFizzBuzz());
        System.out.println("main end");
    }

    static class PrintFizz implements Runnable{
        public void run(){
            System.out.println("fizz");
        }
    }

    static class PrintBuzz implements Runnable{
        public void run(){
            System.out.println("buzz");
        }
    }

    static class PrintFizzBuzz implements Runnable{
        public void run(){
            System.out.println("fizzbuzz");
        }
    }
}
