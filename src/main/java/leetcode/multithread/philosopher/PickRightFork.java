package leetcode.multithread.philosopher;

public class PickRightFork implements Runnable {
    public void run() {
        String name = Thread.currentThread().getName();
        String index = String.valueOf(name.charAt(7));
        System.out.println(index + "拿起右边" + index + "叉子");
    }
}
