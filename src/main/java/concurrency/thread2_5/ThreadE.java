package concurrency.thread2_5;

public class ThreadE extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5; i++) {
            System.out.println("后台线程E第" + i + "次执行");
        }
        try {
            Thread.sleep(7L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
