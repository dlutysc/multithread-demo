package concurrency.thread2_7;

public class LocalVar {
    // 1：通过匿名内部类覆盖ThreadLocal的initialValue()的方法，指定初始值
//    private ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
//        public Integer initialValue(){
//            return 0;
//        }
//    };

//    private ThreadLocal<Integer> seqNum = ThreadLocal.withInitial(() -> 0);
    private NamedThreadLocal<Integer> seqNum = new NamedThreadLocal<>("ysc");

    public ThreadLocal<Integer> getThreadLocal(){
        return seqNum;
    }

    // 2:获取下一个序列值
    public int getNextNum(){
        if (seqNum.get() == null) {
            seqNum.set(0);
        }
        System.out.println(seqNum.toString());
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }
}
