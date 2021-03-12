package learn.jvm.demo;

/**
 * @author Administrator
 */
// 类信息 版本 类本身信息 类和接口的全限定名
public class OtherConstTest {

    // 类信息 字段  都会有字段名称和描述符

    // 实例变量 字面值和文本字符串将添加到 方法区的静态常量池
    // 字面值 添加到class pool
    public int num1 = 1;
    // 文本字符串 添加到class pool 运行时会在stirng pool中创建
    public String str1 = "abcdef";

    public Object obj1 = new Object();

    // 静态变量 添加到方法区
    // 添加到类变量
    public static Integer num2 = 124;
    // 添加到方法区的静态常量池
    public static String str2 = "ABCDEF";
    // 添加到类变量
    public static Object obj2 = new Object();

    // 常量 都添加到方法区的静态常量池
    public final Integer NUM = 2895;
    public final String STR = "abc";
    public final Object OBJ = new Object();

    // 类信息 方法
    public void run() {
        // 局部变量
        int num3 = 124;
        Integer tnum = 124;
        final int num5 = 666;

        // 字符串常量池
        String str3 = "abcdef";
        // 字符串常量池
        String str4 = "test";

        // true 证明运行时 在常量池
        System.out.println(num2 == tnum);

        //  true 证明运行时 在常量池
        System.out.println(str1 == str3);

    }

    // 类信息 方法 方法的名称和描述符
    public static void demo() {}

    // 类信息 静态方法 方法的名称和描述符
    public static void main(String[] args) {
        new OtherConstTest().run();
    }
}
