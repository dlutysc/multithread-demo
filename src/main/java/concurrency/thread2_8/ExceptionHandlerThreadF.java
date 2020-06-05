package concurrency.thread2_8;

public class ExceptionHandlerThreadF implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("an exception has been captured\n");
        System.out.printf("tnread:%s\n", t.getId());
        System.out.printf("exception:%s:%s\n",e.getClass().getName(),e.getMessage());
        System.out.printf("stack trace:\n");
        e.printStackTrace(System.out);
        System.out.printf("thread status:%s\n",t.getState());
    }
}
