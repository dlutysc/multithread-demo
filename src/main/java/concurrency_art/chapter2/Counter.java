package concurrency_art.chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
/**
 *  @Author 书作者
 *  @Date 2020/6/1 17:14
 *  @Description
 */
public class Counter {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    cas.count();
                    cas.safeCount();
                }
            });
            ts.add(t);
        }
        for (Thread t : ts) {
            t.start();

        }
        // 等待所有线程执行完成
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * 使用CAS实现线程安全计数器
     */
    private void safeCount() {
        for (;;) {
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
        //atomicI.getAndIncrement(); // 点开此方法，查看源代码，也是有一个循环，直到更新成功循环才终止，跟上面的方法本质是一样的
        //atomicI.incrementAndGet();
    }

    /**
     * 非线程安全计数器
     */
    private void count() {
        i++;
    }
}
