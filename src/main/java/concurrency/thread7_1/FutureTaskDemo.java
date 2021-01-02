package concurrency.thread7_1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskDemo {
    public static void main(String[] args)throws InterruptedException, ExecutionException {
        SonTask task1 = new SonTask("线程1");
        FutureTask<String> futureTask = new FutureTask<>(task1);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyRun(), 22);
        new Thread(futureTask1).start();
        System.out.println("result_" + futureTask1.get());
    }
}

class MyRun implements Runnable{
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("特定线程2完成");
    }
}

class SonTask implements Callable<String>{
    private String name;
    SonTask(String name){
        this.name = name;
    }
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        System.out.println(name + "任务计算完成");
        return "result_11";
    }
}
