package learn.jvm.demo;

class SuperClass {
    protected static int num = 10;

    static {
        num = 11;
    }
}

class SubClass extends SuperClass {
    static {
        num = 12;
    }
}

public class JvmInitialization {

    public static void main(String[] args) {
        System.out.println(SubClass.num);
    }
}
