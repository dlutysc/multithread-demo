package concurrency.thread2_7;

/**
 * 把书上的原有代码拆开
 */
public class ThreadMain {
    public static void main(String[] args) {
        LocalVar localVar = new LocalVar();
        TestClient t1 = new TestClient(localVar);
        TestClient t2 = new TestClient(localVar);
        TestClient t3 = new TestClient(localVar);
        t1.start();
        t2.start();
        t3.start();
    }
}
