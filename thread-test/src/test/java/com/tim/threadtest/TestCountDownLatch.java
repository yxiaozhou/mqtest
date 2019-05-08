package com.tim.threadtest;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new LatchDemo(countDownLatch).start();
        }
        try {
            countDownLatch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为："+(end - start));
    }
}

class LatchDemo extends Thread {

    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        synchronized (this){
            try{
                for (int i = 0; i < 50; i++) {
                    if (i % 2 == 0) {
                        System.out.println(i);
                    }
                }
            }finally{
                latch.countDown();
            }
        }
    }
}