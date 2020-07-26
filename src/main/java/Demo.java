/**
 *  @Author chasein
 *  @Date 2020/7/26 21:28
 *  @Description 验证synchronized 的原子性，thread1执行add方法结束后，thread2才可以执行add方法，
 *  就是thread1执行add时不可中断(好像不可以这样理解??),因为thread1没有执行完时，thread2就可以执行，只不过不能执行同步的代码块
 *
 */
public class Demo {
    static Demo demo = new Demo();
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runner1(), "thread1");
        Thread thread2 = new Thread(new Runner2(), "thread2");
        thread1.start();
        thread2.start();
    }

    static class Runner1 implements Runnable{
        @Override
        public void run() {
           demo.add();
        }
    }

    static class Runner2 implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10 ; i++) {
                System.out.println( i + " thread 22222");

            }
            demo.add();
        }
    }

    public synchronized void add(){
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
