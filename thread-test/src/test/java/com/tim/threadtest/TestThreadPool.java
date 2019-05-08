package com.tim.threadtest;

import java.util.concurrent.*;

public class TestThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1.创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5 );
//
//        Future<Integer> future = pool.submit(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                int num = 0;
//                for (int i = 0; i < 100; i++) {
//                    num +=i;
//                }
//                return num;
//            }
//        });
//
//        System.out.println(future.get());
//
//        pool.shutdown();

        ThreadPoolDemo tpd = new ThreadPoolDemo();

        // 2.为线程池中的线程分配任务
        for (int i = 0; i < 20; i++) {
            pool.submit(tpd);
        }

        // 3.关闭线程池
        pool.shutdown();

    }
}
class ThreadPoolDemo implements Runnable{
    private int i = 0;
    @Override
    public void run() {
        for (int j = 0; j < 20; j++) {
            System.out.println(Thread.currentThread().getName() + ":" +j );
        }
    }
}
