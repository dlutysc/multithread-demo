package leetcode.multithread.philosopher;

public class Eat implements Runnable {
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String name = Thread.currentThread().getName();
        String index = String.valueOf(name.charAt(7));

        System.out.println(index + " 吃面");
    }
}
