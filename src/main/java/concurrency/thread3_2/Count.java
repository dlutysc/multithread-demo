package concurrency.thread3_2;

public class Count {
    public int num = 0;
    public synchronized void add(){
        //可以注释掉
//        try {
//            Thread.sleep(51);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        num += 1;
        System.out.println(Thread.currentThread().getName() + "-" + num);
    }
}
