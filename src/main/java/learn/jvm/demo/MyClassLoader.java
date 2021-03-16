package learn.jvm.demo;

import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            System.out.println(name);
            InputStream is = getClass().getResourceAsStream(name);
            if(is == null) {
                return super.loadClass(name);
            }
            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(name, b, 0, b.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException ();
        }
    }

    public static void main(String[] args) throws Exception {
        ClassLoader loader = new MyClassLoader();
        Object obj = loader.loadClass("learn.jvm.demo.Math").newInstance();
        System.out.println(obj);
    }
}

final class Math {
    public void out() {
        System.out.println("Math out");
    }
}
