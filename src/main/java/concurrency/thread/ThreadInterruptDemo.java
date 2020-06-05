package concurrency.thread;


public class ThreadInterruptDemo implements Runnable {
    public void run() {
        boolean stop = false;
        while (!stop) {
            System.out.println("my thread is running...");
            long time = System.currentTimeMillis();
            while (System.currentTimeMillis() - time < 1000) {
                //让循环持续一段时间，使上面的话打印次数少一点
            }
            if (Thread.currentThread().isInterrupted()) {
                //需要线程本身处理一下它的终止状态
                break;
            }
//            try {
//                Thread.sleep(300L);
//            } catch (InterruptedException e) {
//                break;
//            }
        }
        System.out.println("my thread exiting under request");
    }

    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(new ThreadInterruptDemo(), "InterruptDemo thread");
        System.out.println("starting thread...");
        thread.start();
        Thread.sleep(5000L);
        System.out.println("interrupting thread...");
        // main线程对对thread线程进行中断
        thread.interrupt();
        System.out.println("线程是否中断：" + thread.isInterrupted());
        Thread.sleep(3000L);
        System.out.println("stopping application");
    }
}
