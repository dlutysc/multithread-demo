package concurrency.thread3_2;

public class ThreadG extends Thread{
    private Count count;
    public ThreadG(Count count){
        this.count = count;
    }
    public void run(){
        count.add();
    }
}
