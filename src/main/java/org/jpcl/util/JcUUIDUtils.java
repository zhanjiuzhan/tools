package org.jpcl.util;

import java.util.UUID;

/**
 * @author Administrator
 */
public class JcUUIDUtils {

    /**
     * 取得一个UUID
     * @return
     */
    private String generate32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
}
