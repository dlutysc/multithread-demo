package concurrency.thread3_10;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(0);
        System.out.println("[1] " + ai.get());
        System.out.println(ai.addAndGet(2)); // 先加再get
        System.out.println(ai.incrementAndGet()); //跟++i类似
        System.out.println("[2] " + ai.getAndSet(5));
        System.out.println(ai.get());
        System.out.println("[3] " + ai.getAndIncrement()); //跟i++类似
        System.out.println(ai.get());
        System.out.println("[4] " + ai.getAndDecrement()); //跟i--类似
        System.out.println(ai.get());
        System.out.println("[5] " + ai.getAndAdd(10));
        System.out.println("[6] " + ai.get());
    }
}
