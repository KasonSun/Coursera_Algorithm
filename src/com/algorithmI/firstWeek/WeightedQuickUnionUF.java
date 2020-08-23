package com.algorithmI.firstWeek;

/**
 * 带权快速合并实现
 */
public class WeightedQuickUnionUF {
    private int id[];//节点数组
    private int sz[];//保存以当前节点为根节点的树种节点的个数
    private int count;//连通分量的个数

    //初始化
    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }

        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }


    //判断该节点是否为树根节点（树根节点特性：树根节点id数组值即为自己id值）
    private int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

    //判断两个节点是否联通
    public boolean connected(int p, int q) {
        return root(p) == root(q);//利用树根节点特性判断，有相同的树根节点即为联通
    }

    //合并
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }

        count--;
    }
}
