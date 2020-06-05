package concurrency.thread5_9;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(3);
        Worker worker1 = new Worker("jack", latch);
        Worker worker2 = new Worker("rose", latch);
        Worker worker3 = new Worker("json", latch);
        worker1.start();
        worker2.start();
        worker3.start();
        latch.await();
        System.out.println("main thread end!");
    }

    static class Worker extends Thread{
        private String workName;
        private CountDownLatch latch;

        public Worker(String workName, CountDownLatch latch){
            this.workName = workName;
            this.latch = latch;
        }

        public void run(){
            try {
                System.out.println("worker: " + workName + " begin");
                Thread.sleep(1000);
                System.out.println("worker: " + workName + " end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }
    }
}


