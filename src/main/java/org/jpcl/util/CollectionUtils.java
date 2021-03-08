package org.jpcl.util;

import java.util.Collection;
import java.util.List;

/**
 * 集合的工具类
 */
final public class CollectionUtils {
    private CollectionUtils() {}

    /**
     * 判断一个集合是否是空的集合
     * @param collection
     * @return
     */
    public static boolean isNullCollection(Collection collection) {
        if (collection == null || collection.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotNullCollection(Collection collection) {
        return !isNullCollection(collection);
    }

    /**
     * 取得集合中的第一个元素
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> T getFirstByCollection(Collection<T> collection) {
        if (isNotNullCollection(collection)) {
            if (collection instanceof List) {
                return ((List<T>) collection).get(0);
            }
            // TODO
        }
        return null;
    }
}
