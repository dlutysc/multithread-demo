package concurrency.thread2_7;

/**
 * 把书上的原有代码拆开
 */
public class ThreadMain {
    public static void main(String[] args) {
        LocalVar localVar = new LocalVar();
        System.out.println("thread{" + Thread.currentThread().getName() + "}" + localVar.getNextNum());
        TestClient t1 = new TestClient(localVar, "one");
        TestClient t2 = new TestClient(localVar, "two");
        TestThread t3 = new TestThread(localVar, "three");
        t1.start();
        t2.start();
        t3.start();
    }

    static class TestThread extends Thread{
        private LocalVar localVar;
        public TestThread(LocalVar localVar, String name){
            this.localVar = localVar;
            this.setName(name);
        }

        public void run(){
            for (int i = 0; i < 4; i++) {
                System.out.println("thread[" + Thread.currentThread().getName() +"]-->localVar["
                        + localVar.getNextNum() + "]");
            }
            localVar.getThreadLocal().remove(); //每个线程用完时要记得删除
        }
    }
}
