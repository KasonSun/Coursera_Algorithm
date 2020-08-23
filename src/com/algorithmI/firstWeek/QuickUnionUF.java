package com.algorithmI.firstWeek;

/**
 * 快速合并实现：优化快速查找，利用森林-树的根实现(id[i]的值即为根节点)
 * 时间复杂度：初始化N；合并N+；查找N
 */
public class QuickUnionUF {
    private int id[];

    //初始化
    public QuickUnionUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
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
        id[i] = j;//将第一个对象的id值设为第二个对象的id值，实际将q作为p的根节点
    }
}
