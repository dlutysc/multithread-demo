package concurrency.thread3_11;

/**
 * 线程不安全的，不正确的写法
 */
public class Singleton1 {
    private static Singleton1 instance;
    private Singleton1(){}
    public static Singleton1 getInstance(){
        if (instance == null) { //线程A执行
            instance = new Singleton1(); //线程B执行
        }
        return instance;
    }
}
