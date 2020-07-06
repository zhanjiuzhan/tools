package org.jpcl.util;

/**
 * @author Administrator
 */
public class JcLogUtils {
    public static String start(String msg, String... str) {
        return "[ 开始 ] -> " + msg + log(str);
    }

    public static String end(String msg) {
        return "[ 结束 ] -> " + msg;
    }

    public static String log(String... str) {
        if (str.length < 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < str.length; i++) {
            stringBuilder.append(str[i] + ": ");
            stringBuilder.append(str[++i] + ", ");
        }
        return stringBuilder.substring(0, stringBuilder.length() -2) + "]";
    }

}
