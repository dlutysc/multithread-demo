package concurrency.thread3_5;


public class ReentrantLockDemo {
    public static void main(String[] args) {
        final Count ct = new Count();
        for (int i = 0; i < 2; i++) {
            new Thread(){
                @Override
                public void run(){
                    ct.get();
                }
            }.start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(){
                @Override
                public void run(){
                    ct.put();
                }
            }.start();
        }
    }
}
