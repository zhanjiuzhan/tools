package learn.jvm.demo;

class JvmParent {

    public static int num1 = 10;

    static { System.out.println("父类的静态成员初始化块 1 ！"); }

    { System.out.println("父类的构造函数初始化块!"); }

    static {
        num1 = 20;
        System.out.println("父类的静态成员初始化块 2 ！");
        num2 = 20;
        // System.out.println(num2); 非法的前向引用，可以赋值，不可调用
    }

    public static int num2 = 10;

    public JvmParent() {
        System.out.println("父类的构造函数");
    }
}

public class JvmClassInitialization extends JvmParent {

    static { System.out.println("子类的静态成员初始化块 1 ！"); }

    { System.out.println("子类的构造函数初始化块!"); }

    static { System.out.println("子类的静态成员初始化块 2 ！");  }

    public JvmClassInitialization() {
        // 这里其实默认是有super()的
        System.out.println("子类的构造函数");
        System.out.println("num1 : " + num1);
        System.out.println("num2 : " + num2);
    }

    public static void main(String[] args) {
        new JvmClassInitialization();
    }
}

/*
父类的静态成员初始化块 1 ！
父类的静态成员初始化块 2 ！
子类的静态成员初始化块 1 ！
子类的静态成员初始化块 2 ！
父类的构造函数初始化块!
父类的构造函数
子类的构造函数初始化块!
子类的构造函数
num1 : 20
num2 : 10
 */
