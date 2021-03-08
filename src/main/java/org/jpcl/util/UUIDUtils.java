package org.jpcl.util;

import java.util.UUID;

/**
 * @author Administrator
 */
public class UUIDUtils {

    /**
     * 取得一个UUID
     * @return
     */
    public static String generate32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
}
