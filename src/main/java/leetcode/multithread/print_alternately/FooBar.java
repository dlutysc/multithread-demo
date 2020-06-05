package leetcode.multithread.print_alternately;

class PrintFoo implements Runnable{
    public void run() {
        System.out.print("foo");
    }
}

class PrintBar implements Runnable{
    public void run() {
        System.out.println("bar");
    }
}

/**
 * 使用Object的wait(),notifyAll()
 */
public class FooBar {
    private int n;
    private Object lock = new Object();
    private boolean fooTurn = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock){
                if (!fooTurn){
                   lock.wait();
                }
                fooTurn = false;
                printFoo.run();
                lock.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock){
                if (fooTurn){
                    lock.wait();
                }
                fooTurn = true;
                printBar.run();
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        final Runnable printFoo = new PrintFoo();
        final Runnable printBar = new PrintBar();
        final FooBar fooBar = new FooBar(10);
        //注释掉的这个方法为什么不行？？
//        try {
//            fooBar.foo(printFoo);
//            fooBar.bar(printBar);
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        new Thread(() -> {
            try {
                fooBar.foo(printFoo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fooBar.bar(printBar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
