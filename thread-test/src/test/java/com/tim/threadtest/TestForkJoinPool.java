package com.tim.threadtest;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class TestForkJoinPool {

    public static void main(String[] args) {
//        ForkJoinPool pool = new ForkJoinPool();
//        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L,100000L);
//        Long sum = pool.invoke(task);
//        System.out.println(sum);

        System.out.println(LongStream.rangeClosed(0, 100000L).parallel().reduce(0, Long::sum));
    }

}
class ForkJoinSumCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;

    private static final long THURSHOLD = 1000L; // 临界值

    public ForkJoinSumCalculate(long start,long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if(length <= THURSHOLD){
            long sum = 0L;
            for (long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        }else {
            long middle = (start + end) / 2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start,middle);
            left.fork(); // 进行拆分，同时压入线程队列

            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle+1,end);
            right.fork(); // 进行拆分，同时压入线程队列

            return left.join() + right.join();
        }

    }
}