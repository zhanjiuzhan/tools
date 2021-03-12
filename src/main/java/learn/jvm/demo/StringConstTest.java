package learn.jvm.demo;

/**
 * @author Administrator
 */
public class StringConstTest {

    private static void demo1() {
        // 字面值方式创建字符串
        String str1 = "abc";  // 将在字符串常量池中寻找 "abc" 若没有则在堆中创建字符串对象 "abc" 并将该对象的地址存储到字符串常量池中并返回给变量
        String str2 = "abc";  // 若有直接从常量池中返回 "abc" 的地址
        System.out.println(str1 == str2); // 结果 true
    }


    private static void demo2() {
        // 下面会直接生成 "ABCDEFG" 存储引用到字符串常量池中 "ABC"  "DEF"  "G" 均不会创建
        String str1 = "ABC" + "DEF" + "G";
        String str2 = "ABCDEFG";
        System.out.println(str1==str2);
    }

    private static void demo3() {
        String str1 = "abc";  // 字符串常量池中将创建"abc"
        // 将在堆中new一个对象返回地址引用给str2，而new的这个对象中value[] 指向字符串常量池中的"abc"
        // 这里共创建了一个对象
        String str2 = new String("abc");
        // 字符串常量池中创建 "def"  堆中创建对象返回引用给str3 这里共创建了2个对象
        String str3 = new String("def");
        System.out.println(str1 == str2);
    }


    public static void demo4() {
        String str1 = "a";   // 保存在常量池中
        String str2 = "b";   // 保存在常量池中
        final String _str1 = "a";
        final String _str2 = "b";
        String str3 = str1 + str2;    // str1 str2 是变量 动态调用 生成新的对象
        String _str3 = _str1 + _str2;  // _str1 _str2 常量 编译时替换为 "a" + "b"了 所以结果保存在常量池了
        String str4 = "ab";  // 常量池已经有了 取出引用赋值
        System.out.println(_str1 == str1);   // true
        System.out.println(str3 == str4);    // false
        System.out.println(_str3 == str4);   // true
    }

    private static final String str1;
    private static final String str2;
    static {
        str1 = "1";
        str2 = "2";
    }
    public static void demo5() {
        String str = "12"; // 存储在常量池中
        // 静态变量在编译期不会执行 所以可以看作是变量 运行时 动态调用 生成新的对象
        String tmp = str1 + str2;
        System.out.println(str == tmp); // false
    }

    public static void demo6() {
        String str = "abcdef";
        String str1 = "abc";
        String str2 = "def";
        String str3 = "abc" + str2;  // str2变量 动态调用
        String str4 = "abc" + new String("def"); // 常量池和堆中的引用做 + 会动态调用

        System.out.println(str == str3);  // false
        System.out.println(str == str4);  // false
    }

    public static void demo7() {
        String str = "abc"; // 放入常量池中
        // 现在常量池中找"abc" 找到的话直接返回引用地址给str1 没找到创建保存到常量池中 并返回引用地址
        // intern() 运行时动态常量创建
        String str1 = new String("abc").intern();
        System.out.println(str == str1); // true
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
        demo6();
        demo7();
    }
}
