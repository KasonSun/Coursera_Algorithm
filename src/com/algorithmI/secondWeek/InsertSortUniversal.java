package com.algorithmI.secondWeek;


import java.util.Arrays;
import java.util.Scanner;

/**
 * 插入排序
 * 思想：（向前逐步交换相邻元素）
 *      想象打扑克牌，一张一张地把牌插入到适当地位置。在计算机的视线中，为了要给插入的元素腾出空间，
 *      我们需要将其余所有元素在插入之前都向后移动一位。插入排序所需要的时间取决于输入元素中的初始顺序。
 */
public class InsertSortUniversal {
    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j >= 0 && less(a[j], a[j - 1]); j--) {
                exchange(a, j, j - 1);
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w)< 0;
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean isSorted(Comparable[] a) { // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }


    //测试
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("please input array length:");
        Integer n = sc.nextInt();
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sort(a);
        System.out.println("whether array is in order: " + isSorted(a));
        System.out.println("select sort array is:" + Arrays.toString(a));
    }
}
