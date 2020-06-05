package leetcode.multithread.philosopher;

public class PickLeftFork implements Runnable {
    public void run() {
        String name = Thread.currentThread().getName();
        String index = String.valueOf(name.charAt(7));
        int i = Integer.parseInt(index);
        int j = (i + 1) % 5;
        System.out.println(index + "拿起左边" + j + "叉子");
    }
}
