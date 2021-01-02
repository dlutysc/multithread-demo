package concurrency.thread2_7;

public class TestClient extends Thread{
    private LocalVar localVar;
    public TestClient(LocalVar localVar, String name){
        this.localVar = localVar;
        this.setName(name);
    }

    public void run(){
        for (int i = 0; i < 3; i++) {
            // 4:每个线程打出3个序列值
            System.out.println("thread[" + Thread.currentThread().getName() +"]-->localVar["
                    + localVar.getNextNum() + "]");
        }
        localVar.getThreadLocal().remove(); //每个线程用完时要记得删除
    }
}
