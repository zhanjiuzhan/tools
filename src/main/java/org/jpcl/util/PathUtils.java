package org.jpcl.util;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Administrator
 */
public class PathUtils {

    /**
     * 我觉得没啥用
     * @return
     */
    public static String getResourcesRealPath() {
        return System.getProperty("user.dir") + "\\src\\main\\resources";
    }

    public static String getResourcesRealPath1() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("tmp.txt");
        return classPathResource.getURI().getPath();
    }

    public String getResourcesRealPath2() throws IOException {
        // 方法1：获取文件或流
        //this.getClass().getResource("/")+fileName;
        this.getClass().getResourceAsStream("tmp.txt");
        // 方法2：获取文件
        File file = org.springframework.util.ResourceUtils.getFile("classpath:test.txt");
        // 方法3：获取文件或流
        ClassPathResource classPathResource = new ClassPathResource("test.txt");
        classPathResource .getFile();
        classPathResource .getInputStream();

        // >>>>>>>>>>>>>>>> 下面方法可以读取jar包下文件 假设resources目录下有一个test.txt文件，首先获得当前的类加载器，通过类加载器读取文件。

        // 方法1
        InputStream io1 = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.txt");
        // 方法2
        InputStream io2 = getClass().getClassLoader().getResourceAsStream("test.txt");
        return null;
    }

    public static boolean isWindows() {
        boolean flag = false;
        if (System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {
            flag = true;
        }
        return flag;
    }
    public static void main(String[] args) throws IOException {
        System.out.println(PathUtils.class.getResource("/"));
    }
}
