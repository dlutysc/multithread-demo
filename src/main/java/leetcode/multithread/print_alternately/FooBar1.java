package leetcode.multithread.print_alternately;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock和Condition
 * 使用Condition的await(),signal()
 */
public class FooBar1 {
    private int n;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean isEmpty = true;

    public FooBar1(int n){
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        lock.lock();
        for (int i = 0; i < n; i++) {
            while (!isEmpty){
                condition.await();
            }
            isEmpty = false;
            printFoo.run();
            condition.signal();
        }
        lock.unlock();
    }

    public void bar(Runnable printBar) throws InterruptedException {
        lock.lock();
        for (int i = 0; i < n; i++) {
            while (isEmpty){
                condition.await();
            }
            isEmpty = true;
            printBar.run();
            condition.signal();
        }
        lock.unlock();
    }
    public static void main(String[] args) {
        final Runnable printFoo = new PrintFoo();
        final Runnable printBar = new PrintBar();
        final FooBar1 fooBar1 = new FooBar1(5);

//        new Thread(){
//            public void run(){
//                try {
//                    fooBar1.foo(printFoo);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();

        new Thread(()->{
            try {
                fooBar1.foo(printFoo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    fooBar1.bar(printBar);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        // 上面注释掉的是老式写法，下面是使用java8的新写法
        new Thread(()->{
            try {
                fooBar1.bar(printBar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
