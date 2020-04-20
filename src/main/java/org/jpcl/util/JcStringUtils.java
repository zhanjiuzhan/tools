package org.jpcl.util;

import java.lang.reflect.Array;

/**
 * @author chenglei
 */
final public class JcStringUtils {

    private JcStringUtils() {}

    /**
     * 判断字符串是否为空, 空格啥的也算字符串
     * @param cs
     * @return
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 判断字符串是否不为空
     * @param cs
     * @return
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 判断字符串是否全空
     * @param css
     * @return
     */
    public static boolean isAllEmpty(CharSequence... css) {
        if (css == null || Array.getLength(css) == 0) {
            return true;
        } else {
            CharSequence[] var1 = css;
            int var2 = css.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence cs = var1[var3];
                if (isNotEmpty(cs)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 取得字符串的长度
     * @param cs
     * @return
     */
    public static int length(CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }

    /**
     * 取得字符串有效 且是否是空白字符串
     * 空白符包含：空格、 \t(在文中该处插入一个 tab 键)、\n(在文中该处换行)、
     * \r(在文中该处插入回车)、\f(在文中该处插入换页符)
     * @param cs
     * @return
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen = length(cs);
        if (strLen == 0) {
            return true;
        } else {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 判断是否是空白字符
     * @param cs
     * @return
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }
}
