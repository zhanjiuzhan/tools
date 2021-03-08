package org.jpcl.util;

import java.util.regex.Pattern;

/**
 * @author Administrator
 */
final public class RegexUtils {

    private RegexUtils() {}

    public final static String keyWord = "^[a-zA-Z_]\\w{0,31}$";

    public final static boolean isKeyWord(String str) {
        if (StringUtils.isNotEmpty(str)) {
            return Pattern.matches(keyWord, str);
        }
        return false;
    }
}
