package concurrency.thread2_8;

public class ThreadMain {
    public static void main(String[] args) {
        ThreadF task = new ThreadF();
        Thread thread = new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandlerThreadF());
        thread.start();
    }
}
