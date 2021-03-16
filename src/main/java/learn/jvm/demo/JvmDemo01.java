package learn.jvm.demo;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.StringTokenizer;

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

    //ExtClassLoader类中获取路径的代码
    private static File[] getExtDirs() {
        //加载<JAVA_HOME>/lib/ext目录中的类库
        String s = System.getProperty("java.ext.dirs");
        File[] dirs;
        if (s != null) {
            // StringTokenizer 用于分隔字符串
            StringTokenizer st = new StringTokenizer(s, File.pathSeparator);
            int count = st.countTokens();
            dirs = new File[count];
            for (int i = 0; i < count; i++) {
                dirs[i] = new File(st.nextToken());
            }
        } else {
            dirs = new File[0];
        }
        return dirs;
    }

    public static void main(String[] args) {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for(URL url : urls){
            System.out.println(url.toExternalForm());
        }
        System.out.println("jvm 局部变量表");

        Arrays.toString(getExtDirs());
    }
}
