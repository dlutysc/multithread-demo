package concurrency.thread2_5;

public class ThreadD extends Thread {
    @Override
    public void run() {
        super.run();
        for (long i = 0; i < 9999999L; i++) {
            System.out.println("后台线程D第" + i + "次执行");
        }
        try {
            Thread.sleep(7L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
