package learn.jvm.demo;

/**
 * @author Administrator
 */
public class JvmDemo01 {

    public int demo1(Object obj) {
        byte b = 1;
        short s = 2;
        int i = 1;
        long lo = 64L;
        boolean flag = true;
        float f = 3.14f;
        double dou = 3.14159265;
        char c = 'A';
        for (int j = i; j < lo; j++) {
            // 无
        }
        return i + b;
    }


    public static void main(String[] args) {
        System.out.println("jvm 局部变量表");
    }
}
