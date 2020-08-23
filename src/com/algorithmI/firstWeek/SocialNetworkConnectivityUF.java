package com.algorithmI.firstWeek;

import java.util.Scanner;

/**
 * 第一周编程作业第一题：SocialNetworkConnectivity
 */
public class SocialNetworkConnectivityUF {
    private int[] id;//保存节点的根
    private int[] sz;//保存以当前节点为根节点的树（连通分量）中元素个数
    private int count;//保存连通分量个数或者剩余的root节点个数
    private int[][] timeStamp = {{1, 2}, {1, 0}, {7, 8}, {6, 7}, {1, 5}, {4, 9}, {0, 3}, {0, 4}, {1, 6}, {5, 9}};

    public SocialNetworkConnectivityUF(int N) {
        count = N;//初始化各自为一个连通分量
        id = new int[N];
        sz = new int[N];

        //初始化节点数组，数组值为索引号
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }

        //初始化以当前节点为根节点的元素数目，数组存储，初始化各自为一棵单节点的树
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }

    //返回给定元素的根节点
    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];//压缩路径
            i = id[i];
        }
        return i;
    }

    //合并
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) {
            return;
        }
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];//a+=b <==> a=a+b
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    //主函数
    public static void main(String[] args) {
        SocialNetworkConnectivityUF snc = new SocialNetworkConnectivityUF(10);
        Scanner sc = new Scanner(System.in);
        int count = 1;//记录时间
        while (true) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            snc.union(p, q);
            if (snc.count == 1) {
                System.out.println("所有节点已连接：log" + count);
                break;
            }
            System.out.println(snc.count);
            count++;
        }
    }
}
