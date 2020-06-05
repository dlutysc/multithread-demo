package concurrency.thread3_4;

public class Count {
    public int num = 0;
    public synchronized void methodA(){
        try {
            Thread.sleep(51);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num += 1;
        System.out.println(Thread.currentThread().getName() + "-" + num);
    }

    public void methodB(){
        synchronized (this){
            try {
                Thread.sleep(52);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num += 1;
            System.out.println(Thread.currentThread().getName() + "-" + num);
        }

    }
}
