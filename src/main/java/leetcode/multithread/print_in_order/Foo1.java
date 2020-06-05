package leetcode.multithread.print_in_order;

public class Foo1 {
    boolean firstFinished = false;
    boolean secondFinished = false;
    Object lock = new Object();

    public Foo1() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized(lock){
            printFirst.run();
            firstFinished = true;
            lock.notifyAll();
        }

    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized(lock){
            while(!firstFinished){
                lock.wait();
            }
            printSecond.run();
            secondFinished = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized(lock){
            while(!secondFinished){
                lock.wait();
            }
            printThird.run();
        }

    }

    public static void main(String[] args) {
        Runnable
                printFirst = new PrintFirst(),
                printSecond = new PrintSecond(),
                printThird = new PrintThird();
        Foo1 foo1 = new Foo1();
        try {
            foo1.first(printFirst);
            foo1.second(printSecond);
            foo1.third(printThird);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
