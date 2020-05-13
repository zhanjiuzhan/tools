package org.jpcl.util;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
final public class JcSortUtils {
    private JcSortUtils() {}

    /**
     * 根据list中的一个属性进行排序
     * @param list
     * @param fun
     * @param <U> 该字段类型试可以排序的类型
     * @return
     */
    public static <T, U extends Comparable<? super U>> List<T> sortedList(List<T> list, Function<? super T, ? extends U> fun) {
        return list.stream().sorted(Comparator.comparing(fun)).collect(Collectors.toList());
    }

    /**
     * 根据list中的一个属性进行排序 逆序
     * @param list
     * @param fun
     * @param <U> 该字段类型试可以排序的类型
     * @return
     */
    public static <T, U extends Comparable<? super U>> List<T> sortedListR(List<T> list, Function<? super T, ? extends U> fun) {
        return list.stream().sorted(Comparator.comparing(fun).reversed()).collect(Collectors.toList());
    }
}
