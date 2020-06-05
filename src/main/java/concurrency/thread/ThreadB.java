package concurrency.thread;

public class ThreadB implements Runnable {
    public void run() {
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread curThread = Thread.currentThread();
        String curThreadName = curThread.getName();
        System.out.println("这是线程的名称：" + curThreadName);
        System.out.println("返回当前线程： " + curThreadName + "的线程组中活动线程数目：" + Thread.activeCount());
        System.out.println("返回当前线程： " + curThreadName + "的标识符：" + curThread.getId());
        System.out.println("返回当前线程： " + curThreadName + "的优先级：" + curThread.getPriority());
        System.out.println("返回当前线程： " + curThreadName + "的状态：" + curThread.getState());
        System.out.println("返回当前线程： " + curThreadName + "所属的线程组：" + curThread.getThreadGroup());
        System.out.println("测试线程： " + curThreadName + "是否处于活动状态" + curThread.isAlive());
        System.out.println("返回当前线程： " + curThreadName + "是否是守护线程" + curThread.isDaemon());

        System.out.println("这是线程B");
    }
}
