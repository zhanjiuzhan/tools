package org.jpcl.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
final public class SortUtils {
    private SortUtils() {}

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

    /**
     * 选择排序
     * @param array
     */
    public static void sortBySelection(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }

    /**
     * 冒泡排序
     * @param array
     */
    public static void sortByBubble(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j ++) {
                if (array[j] > array[j+1]) {
                    swap(array, j, j+1);
                }
            }
        }
    }

    /**
     * 插入排序
     * @param array
     */
    public static void sortByInsertion(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > tmp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = tmp;
        }
    }

    /**
     * 归并排序
     * @param array
     * @return
     */
    public static int[] sortByMerge(int[] array) {
        if (array.length < 2) {
            return array;
        } else {
            int tmp = array.length / 2;
            return merge(sortByMerge(Arrays.copyOfRange(array, 0, tmp)),
                    sortByMerge(Arrays.copyOfRange(array, tmp, array.length)));
        }
    }

    private static int[] merge(int[] left, int[] right) {
        int[] tmp = new int[left.length + right.length];

        int l = 0, r = 0, t = 0;
        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                tmp[t++] = left[l++];
            } else {
                tmp[t++] = right[r++];
            }
        }

        while (l < left.length) {
            tmp[t++] = left[l++];
        }

        while (r < right.length) {
            tmp[t++] = right[r++];
        }
        return tmp;
    }

    /**
     * 快速排序方案
     * @param array
     * @param low
     * @param height
     */
    public static void sortByQuick(int[] array, int low, int height) {
        if (low < height) {
            int index = findElementPositionOne(array, low, array.length - 1);
            sortByQuick(array, low, index-1);
            sortByQuick(array, index+1, height);
        }
    }

    public static int findElementPositionOne(int[] array, int low, int height) {
        int position = low;
        int tmp = array[low];
        int sign = low;
        for(; low <= height; low++) {
            if (array[low] < tmp) {
                swap(array, low, position);
                position++;
                sign = low;
            }
        }
        swap(array, sign, position);
        return position;
    }

    public static int findElementPositionTwo(int[] array,  int low, int height) {
        return 0;
    }

    /**
     * 交互数组中 两个下标的元素的值
     * @param array
     * @param index1
     * @param index2
     */
    public static void swap(int[] array, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        array[index1] = array[index1] + array[index2];
        array[index2] = array[index1] - array[index2];
        array[index1] = array[index1] - array[index2];
    }

    public static void main(String[] args) {
        int[] arr = {43, 1, 4, 23, 45, 11, 76, 76, 56, 83, 0, 5};
        sortByQuick(arr, 0 , arr.length-1);
        System.out.println(Arrays.toString(arr));

        int[] arr1 = {43, 1, 4, 23, 45, 11, 76, 76, 56, 83, 0, 5};
        System.out.println(findElementPositionOne(arr1, 0, arr1.length-1));
        System.out.println(Arrays.toString(arr1));
        System.out.println(findElementPositionOne(arr1, 0, 5));
        System.out.println(Arrays.toString(arr1));
    }
}
