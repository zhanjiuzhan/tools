package learn.jvm.demo;

import java.util.HashMap;

/**
 * @author Administrator
 */
public class JvmDemo2 {

    // 无限创建线程 兄弟就别试了 会越来越卡 直到卡死
    // 理论上出  OutOfMemoryError
    public static void demo1() {
        // 无限创建线程
        int i = 0;
        while(true) {
            new Thread(()->{
                try {
                    System.out.println("线程: " + Thread.currentThread().getName() );
                    Thread.sleep(60_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, ("Thread_" + i++)).start();
        }
    }

    // 无限递归 java.lang.StackOverflowError
    public static void demo2() {
        demo2();
    }


    public static void main(String[] args) {
        demo2();
        String[][] str = new String[100000][100000];
    }
}
