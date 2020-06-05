package concurrency.thread3_11;

/**
 * 线程安全，但是高并发性能不是很高的写法
 */
public class Singleton2 {
    private static Singleton2 instance;
    private Singleton2(){}
    public synchronized static Singleton2 getInstance(){
        if (instance == null) { //线程A执行
            instance = new Singleton2(); //线程B执行
        }
        return instance;
    }
}

