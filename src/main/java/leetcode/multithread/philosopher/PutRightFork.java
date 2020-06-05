package leetcode.multithread.philosopher;

public class PutRightFork implements Runnable {
    public void run() {
        String name = Thread.currentThread().getName();
        String index = String.valueOf(name.charAt(7));
        System.out.println(index + "放下右边" + index + "叉子");
    }
}
