package com.algorithmI.firstWeek;

/**
 * 快速查找实现
 * 时间复杂度：初始化N；合并N；查找1（N个元素N次合并，时间复杂度N^2）
 */
public class QuickFindUF {
    private int[] id;

    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < 4; i++) {//将每一个id的值等于其索引
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];//判断两元素是否连接
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {//合并操作：遍历数组中与pid相同的值，并将qid的值赋值给他，完成连接
            if (id[i] == pid) {
                id[i] = qid;
            }
        }

    }
}
