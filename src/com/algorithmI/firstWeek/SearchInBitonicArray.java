package com.algorithmI.firstWeek;

/**
 * bitonic array是搜索一个内部先递增再递减的序列，实现3lgn很简单，先找出最大值用掉lgn，
 * 再从左侧有序数组中查找用掉lgn，未找到则再从右侧有序数组中查找用掉lgn，最坏情况下复杂度就是3lgn
 */
public class SearchInBitonicArray {

    //二分查找最大值
    public static int searchMax(int[] arr, int low, int high) {
        int mid = 0;
        while (low < high) {
            mid = (low + high) / 2;
            if (arr[mid] < arr[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return high;
    }

    //二分搜索左半部分(递增序列)
    public static int searchLeft(int[] arr, int key, int low, int high) {
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

    //二分搜索右半部分（递减序列）
    public static int searchRight(int[] arr, int key, int low, int high) {
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (key < arr[mid]) {
                low = mid + 1;
            } else if (key > arr[mid]) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int searchInBitonicArray(int[] arr, int key, int low, int high) {
        int posMax = searchMax(arr, low, arr.length - 1);
        int posKey = searchLeft(arr, key, low, posMax);
        if (posKey == -1) {
            posKey = searchRight(arr, key, posMax, high);
        }
        return posKey;
    }

    public static void main(String[] args) {
        int[] arr = {6, 7, 8, 9, 5, 4, 3, 2};
        int posKey = searchInBitonicArray(arr, 0, 0, arr.length - 1);
        System.out.println("当前搜索关键值在数组中的位置为：" + posKey);
    }
}
