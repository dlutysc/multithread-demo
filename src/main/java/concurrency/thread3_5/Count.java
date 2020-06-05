package concurrency.thread3_5;

import java.util.concurrent.locks.ReentrantLock;

public class Count {
    final ReentrantLock lock = new ReentrantLock();
    public void get(){
//        final ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock(); //加锁
            System.out.println(Thread.currentThread().getName() + " get begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " get end");
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void put(){
//        final ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " put begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " put end");
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
