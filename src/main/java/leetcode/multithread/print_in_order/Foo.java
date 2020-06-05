package leetcode.multithread.print_in_order;

import java.util.concurrent.atomic.AtomicInteger;

class PrintFirst implements Runnable{
    public void run() {
        System.out.println("first");
    }
}

class PrintSecond implements Runnable{
    public void run() {
        System.out.println("second");
    }
}

class PrintThird implements Runnable{
    public void run() {
        System.out.println("third");
    }
}

public class Foo {
    private AtomicInteger firstJobDone = new AtomicInteger(0);
    private AtomicInteger secondJobDone = new AtomicInteger(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        firstJobDone.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while(firstJobDone.get() != 1){

        }
        printSecond.run();
        secondJobDone.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while(secondJobDone.get() != 1){

        }
        printThird.run();
    }

    public static void main(String[] args) {
        Runnable
            printFirst = new PrintFirst(),
            printSecond = new PrintSecond(),
            printThird = new PrintThird();
        Foo foo = new Foo();
        try {
            foo.first(printFirst);
            foo.second(printSecond);
            foo.third(printThird);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
