package concurrency.thread3_2;

public class ThreadMain {
    public static void main(String[] args) {
        Count count = new Count();
        for (int i = 0; i < 5; i++) {
            ThreadG task = new ThreadG(count);
            task.start();
        }
        try {
            Thread.sleep(1000); //等5个人干完活即等上面5个线程执行完
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5个人干完活：最后的值是 " + count.num);
    }
}
