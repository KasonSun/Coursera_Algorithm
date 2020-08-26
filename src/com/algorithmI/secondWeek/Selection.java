package com.algorithmI.secondWeek;

import java.util.concurrent.LinkedTransferQueue;

/**
 * 选择排序：从未排序的序列中找出最小值与当前的i项对应的值-交换
 */
public class Selection {
    public static void sort(Comparable[] c){
        int n=c.length;
        for (int i = 0; i < n; i++) {
            int min=i;
            for (int j = i+1; j < n; j++) {
                if(less(c[j],c[min])){
                    min=j;
                }
                exchange(c,i,min);
            }
        }
    }

    private static boolean less(Comparable a,Comparable b){

    }

    private static void exchange(Comparable[] c,int i,int j){

    }
}
