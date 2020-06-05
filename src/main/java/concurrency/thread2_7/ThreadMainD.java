package concurrency.thread2_7;

/**
 * 这个是书上的原有例子
 */
public class ThreadMainD {
     //1：通过匿名内部类覆盖ThreadLocal的initialValue()的方法，指定初始值
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        public  Integer initialValue(){
            return 0;
        }
    };

    public ThreadLocal<Integer> getThreadLocal(){
        return seqNum;
    }

    // 2:获取下一个序列值
    public int getNextNum(){
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public static void main(String[] args) {
        ThreadMainD threadMain = new ThreadMainD();
        // 3：3个线程共享threadMain,各自产生序列号
        TestClient t1 = new TestClient(threadMain);
        TestClient t2 = new TestClient(threadMain);
        TestClient t3 = new TestClient(threadMain);
        t1.start();
        t2.start();
        t3.start();
    }

    private static class TestClient extends Thread{
        private ThreadMainD threadMain;
        public TestClient(ThreadMainD threadMain){
            this.threadMain = threadMain;
        }

        public void run(){
            for (int i = 0; i < 3; i++) {
                // 4:每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName() +"]-->threadMain["
                        + threadMain.getNextNum() + "]");
            }
            threadMain.getThreadLocal().remove(); //每个线程用完时要记得删除
        }
    }
}
