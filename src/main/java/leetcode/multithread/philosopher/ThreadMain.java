package leetcode.multithread.philosopher;

public class ThreadMain {
    public static void main(String[] args) {
        final Eat eat = new Eat();
        final PickLeftFork pickLeftFork = new PickLeftFork();
        final PickRightFork pickRightFork = new PickRightFork();
        final PutLeftFork putLeftFork = new PutLeftFork();
        final PutRightFork putRightFork = new PutRightFork();
        final DiningPhilosophers philosophers = new DiningPhilosophers();

        for (int i = 0; i < 5; i++) {
            final int no = i; //匿名内部类不能直接访问i，需要把它转成常量
            new Thread(new Runnable() {
                public void run() {
                    try {
                        philosophers.wantsToEat(no, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


    }
}
