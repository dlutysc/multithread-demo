package concurrency.thread5_11;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) throws Exception {
        final Semaphore semaphore = new Semaphore(1); //一次只允许3个人进行访问
        for (int i = 0; i < 11; i++) {
            final int no = i;
            // 可以用Lambda表达式来表示Runnable接口（函数式接口）的一个实现类，JAVA 8 之前一般是用匿名类实现的
            Runnable thread = () -> {
                try {
                    System.out.println("用户" + no + "连接上了：");
                    Thread.sleep(300);
                    semaphore.acquire(); //获取接下来执行的许可
                    System.out.println("用户" + no + "开始访问后台的程序...");
                    Thread.sleep(1000); //模仿用户访问服务器的过程
                    semaphore.release();
                    System.out.println("用户" + no + "访问结束。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            new Thread(thread).start();
        }
        System.out.println("main thread end!");
    }

//    public void f(){
//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    System.out.println("用户" + no + "连接上了：");
//                    Thread.sleep(300);
//                    semaphore.acquire(); //获取接下来执行的许可
//                    System.out.println("用户" + no + "开始访问后台的程序...");
//                    Thread.sleep(1000); //模仿用户访问服务器的过程
//                    semaphore.release();
//                    System.out.println("用户" + no + "访问结束。");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
}
