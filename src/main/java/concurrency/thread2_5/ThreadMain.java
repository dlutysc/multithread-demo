package concurrency.thread2_5;

public class ThreadMain {
    public static void main(String[] args) {
        ThreadD threadD = new ThreadD();
        ThreadE threadE = new ThreadE();
        threadD.setDaemon(true);

        threadD.start();
        threadE.start();

//        threadE.start();
//        threadD.start();
        Thread mainThread = Thread.currentThread();
        System.out.println("线程D是否为守护线程：" + threadD.isDaemon());
        System.out.println("线程E是否为守护线程：" + threadE.isDaemon());
        System.out.println("线程main是否为守护线程：" + mainThread.isDaemon());

        Thread thread = new Thread(() -> System.out.println("ss"));
        thread.start();
    }
}
