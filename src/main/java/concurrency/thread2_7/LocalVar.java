package concurrency.thread2_7;

public class LocalVar {
    // 1：通过匿名内部类覆盖ThreadLocal的initialValue()的方法，指定初始值
//    private ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
//        public Integer initialValue(){
//            return 0;
//        }
//    };

    private ThreadLocal<Integer> seqNum = ThreadLocal.withInitial(() -> 0);

    public ThreadLocal<Integer> getThreadLocal(){
        return seqNum;
    }

    // 2:获取下一个序列值
    public int getNextNum(){
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }
}
