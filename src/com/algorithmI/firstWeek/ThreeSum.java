package com.algorithmI.firstWeek;

import java.util.Arrays;
import java.util.Random;


/**
 * 3-SUM求解：n个数中3个数和为0的三元组的数量（O（n^2））
 * 暴力算法：三循环，（O（n^3））
 * 改进算法：转换为2-SUM算法，先求两个数的和再再剩余的数中使用二分法查找是否存在和的相反数
 */
public class ThreeSum {


    //初始化一个随机数有正有负的数组
    public static int[] arrayAssign(int[] arr) {
        int random = 0;
        for (int i = 0; i < arr.length; i++) {
            random = new Random().nextInt(10) - 5;
            arr[i] = random;
        }
        return arr;
    }

    //二分搜索
    public static int binarySearch(int key, int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (key < arr[mid]) {
                high = mid - 1;
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    //求和记录满足=0的组数
    public static int sumCount(int[] arr) {
        int count = 0;
        int opposite = 0;//用于存储前连个数和的相反数
        Arrays.sort(arr);//先排序，再搜索
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                opposite = -(arr[i] + arr[j]);
                if (binarySearch(opposite, arr) > j) {//算法判断核心：前两个数和的相反数用二分搜索，比较返回的索引是否在j的后面
                    count++;
                }
            }
        }
        return count;
    }

    //主函数
    public static void main(String[] args) {

        int[] arr = new int[10];
        int[] arrAssign = arrayAssign(arr);
        int count = sumCount(arrAssign);
        System.out.println("长度为" + arrAssign.length + "的3-SUM组数为：" + count);
    }
}
