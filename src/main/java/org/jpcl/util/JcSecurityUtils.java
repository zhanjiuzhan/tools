package org.jpcl.util;

import java.security.MessageDigest;

/**
 * @author Administrator
 */
final public class JcSecurityUtils {
    private JcSecurityUtils() {
    }

    public static String md5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = data.getBytes();
            md.update(b);
            byte[] b2 = md.digest();
            int len = b2.length;
            String str = "0123456789abcdef";
            char[] ch = str.toCharArray();
            char[] chs = new char[len * 2];
            for (int i = 0, k = 0; i < len; i++) {
                byte b3 = b2[i];
                chs[k++] = ch[b3 >>> 4 & 0xf];
                chs[k++] = ch[b3 & 0xf];
            }
            return new String(chs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
