package com.algorithmI.secondWeek;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 希尔排序（直接插入排序的改进算法）
 * 思想：
 * 基于插入排序的快速排序算法。对于大规模乱序的数组插入排序很慢，是因为它只会交换相邻的元素，
 * 因此元素只能一点一点移动。希尔排序改进了插入排序算法，交换不相邻的元素以对数组的局部进行排序，
 * 并最终用插入排序将局部有序的数组排序。 关键：使数组中任意间隔为h的元素都是有序的
 */
public class ShellSort {
    public static void sort(int[] a) {
        int n = a.length;
        int h = n / 2;
        //选取初始增量（相对于数组的规模择优选取）
        /*while(h<n/3){
            h=3*h+1;
        }*/
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exchange(a, j, j - h);
                }
            }
            h /= 2;
            //h/=3;
        }
    }

    private static boolean less(int v, int w) {
        return new Integer(v).compareTo(new Integer(w)) < 0;
    }

    private static void exchange(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean isSorted(int[] a) { // Test whether the array entries are in order.
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
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sort(a);
        System.out.println("whether array is in order: " + isSorted(a));
        System.out.println("shellSort sort array is:" + Arrays.toString(a));
    }
}
