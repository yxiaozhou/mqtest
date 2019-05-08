package com.tim.threadtest;

/**
 * 题目：判断打印的“one” or “two”？
 *
 * 1.两个普通同步方法,两个线程，标准打印，打印 // one two
 * 2.新增Thread.sleep() 给getOne()  ,打印 // one two
 * 3.新增普通方法getThread(),打印 // one two
 * 4.两个普通同步方法，两个Number对象,打印// two one
 * 5.修改getOne() 为静态同步方法,打印 // two one
 * 6.修改两个方法均为静态同步方法，一个Number对象 one two
 * 7.一个静态同步方法，一个非静态同步方法，两个Number对象 two one
 * 8.两个静态同步方法，两个Number对象
 *
 * 线程八锁的关键:
 * ① 非静态方法的锁默认为 this,静态方法的锁对应为Class 实例
 * ② 某一个时刻内，只能有一个线程持有锁，无论几个方法
 */
public class TestThread8Monitor {
    public static void main(String[] args) {
        final Number number = new Number();
        final Number number1 = new Number();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                number.getOne();
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                number.getTwo();
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                number.getThree();
//            }
//        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                number.getFour();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                number1.getFive();
            }
        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                number.getSix();
//            }
//        }).start();
    }
}

class Number{

    public synchronized void getOne(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("One");
    }

    public synchronized void getTwo(){
        System.out.println("Two");
    }

    public void getThree(){
        System.out.println("Three");
    }

    public static synchronized void getFour(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("four");
    }

    public static synchronized void getFive(){
        System.out.println("five");
    }
    public void getSix(){
        System.out.println("six");
    }
}