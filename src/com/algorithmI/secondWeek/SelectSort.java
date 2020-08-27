package com.algorithmI.secondWeek;


import java.util.Arrays;
import java.util.Scanner;

/**
 * 选择排序：从未排序的序列中找出最小值与当前的i项对应的值-跳跃交换
 * Integer、String、Double都实现了Comparable接口,其他类型参考Date的实现
 */
public class SelectSort {
    public static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exchange(a, i, min);
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


    //测试
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("please input array length:");
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        /*while(!sc.hasNext("-1")){
            a[i++]=sc.nextInt();
        }*/
        sort(a);
        System.out.println("select sort array is:" + Arrays.toString(a));
    }

}
