package concurrency.thread;

public class ThreadMain {
    public static void main(String[] args) {
        ThreadB threadB = new ThreadB();
        for (int i = 0; i < 5; ++i){
            new Thread(threadB, "线程名称:" + "(" + i + ")").start();
        }

        Thread threadMain = Thread.currentThread();
        System.out.println("这是主线程");
        String curThreadName = threadMain.getName();
        System.out.println("主线程的名称：" + curThreadName);
        System.out.println("返回主线程" + curThreadName + "的线程组中活动线程数目：" + Thread.activeCount());
        System.out.println("返回主线程" + curThreadName + "的标识符：" + threadMain.getId());
        System.out.println("返回主线程" + curThreadName + "的优先级：" + threadMain.getPriority());
        System.out.println("返回主线程" + curThreadName + "的状态：" + threadMain.getState());
        System.out.println("返回主线程" + curThreadName + "所属的线程组：" + threadMain.getThreadGroup());
        System.out.println("主线程" + curThreadName + "是否处于活动状态" + threadMain.isAlive());
        System.out.println("返回主线程" + curThreadName + "是否是守护线程" + threadMain.isDaemon());
        System.out.println("\n");
        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        ThreadA threadA = new ThreadA();
//        threadA.start(); //启动线程A
//
//        ThreadB threadB = new ThreadB();
//        new Thread(threadB).start(); //启动线程B
//        System.out.println("这是主线程");
//
//        ThreadC threadC = new ThreadC();
//        FutureTask<String> feature = new FutureTask<String>(threadC);
//        new Thread(feature).start(); //启动线程C
//        System.out.println("这是主线程 begin：");
//        //只有主线程get了，主线程才会继续往下执行
//        try {
//            System.out.println("得到的返回结果是："+ feature.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        System.out.println("这是主线程 end");
//    }

}
