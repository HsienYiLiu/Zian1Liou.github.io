import java.util.*;
public class TestThread extends Thread{
    private String name;
    public TestThread(String name){
        this.name = name;
    }
    public void run(){//overrid mathod define in class Thread
        for(;;)
            System.out.println("Hello" + name);
    }
    public static void main(String[] argv){
        Thread t1 = new TestThread("老漁");
        Thread t2 = new Thread(new TestThread("悅悅"));
        //after a Thread was started, it executes run()
        t1.setPriority(Thread.NORM_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        //Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
        t1.start();
        t2.start();
        /*for(;;){
            System.out.println("我是main的THREAD");
        }*/
    }
}