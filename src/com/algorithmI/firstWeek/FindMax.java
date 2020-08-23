package com.algorithmI.firstWeek;

/**
 * 第一周编程作业第二题：Union-find with specific canonical element合并-查找特定规范元素（连通分量中最大值）
 * 思想：使用额外的一个数组来存储包含节点i的连通分量中最大值
 */
public class FindMax {
    private int[] id;//保存节点的根
    private int[] sz;//保存以当前节点为根节点的树（连通分量）中元素个数
    private int[] max;//保存每个节点对应树的所有节点中最大值

    public FindMax(int N) {
        id = new int[N];
        sz = new int[N];
        max = new int[N];
        //初始化节点数组，数组值为索引号
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }

        //初始化以当前节点为根节点的元素数目，数组存储，初始化各自为一棵单节点的树
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }

        //初始化最大值数组
        for (int i = 0; i < N; i++) {
            max[i] = i;
        }
    }

    //返回给定元素的根节点
    public int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];//压缩路径
            i = id[i];
        }
        return i;
    }

    //找出当前节点树中（连通分量中）记录的最大值
    public int findMax(int a) {
        return max[root(a)];
    }

    //加权快速合并，存储最大值
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) {
            return;
        }
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];//a+=b <==> a=a+b
            if (max[i] > max[j]) {
                max[j] = max[i];
            }
        } else {
            id[j] = i;
            sz[i] += sz[j];
            if (max[i] < max[j]) {
                max[i] = max[j];
            }
        }
    }

    public static void main(String[] args) {
        FindMax fm = new FindMax(10);
        fm.union(1, 2);
        fm.union(1, 0);
        fm.union(7, 8);
        fm.union(1, 7);
        System.out.println("包含1节点的连通分量中最大值为：" + fm.findMax(1));

    }
}
