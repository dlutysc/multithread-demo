package concurrency.thread3_11;

/**
 * 线程安全，性能又高，这种写法最为常见
 */
public class Singleton3 {
    private static volatile Singleton3 instance; //为什么要加volatile关键字，原因收藏到cnblogs
    private static byte[] lock = new byte[1];
    private Singleton3(){}

    public static Singleton3 getInstance(){
        if (instance == null) {
            synchronized (lock){
                if (instance == null){
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
