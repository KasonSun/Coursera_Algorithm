package com.algorithmI.firstWeek;

/**
 * 自写SuccessorWithDelete
 */
public class SuccessorWriteBySelf {
    private int[] id;
    private boolean[] isRemove;
    private int num;

    //初始化数组
    public SuccessorWriteBySelf(int N) {
        num = N;
        id = new int[N];
        isRemove = new boolean[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
            isRemove[i] = false;
        }
    }

    //获取根
    public int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

    //合并,区别之前的加权，使用最大值作为节点根
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (i == j) {
            return;
        } else if (i < j) {
            id[i] = j;
        } else {
            id[j] = i;
        }
    }

    //remove方法
    public void remove(int i) {
        isRemove[i] = true;
        //判断相邻节点是否已被移除,移除就合并
        if (i > 0 && isRemove[i - 1] == true) {
            union(i, i - 1);
        }
        if (i < num - 1 && isRemove[i + 1]) {
            union(i, i + 1);
        }
    }

    //getSuccessor方法
    public int getSuccssor(int i) {
        //越界检查
        if (i < 0 || i > num - 1) {
            throw new IllegalArgumentException("访问越界");
        } else if (isRemove[i] == true) {
            if (root(i) + 1 > num - 1) {//当前节点i被移除，且后面的继任者已经超出范围
                return -1;
            } else {
                return root(i) + 1;//返回移除数集中的最大值+1
            }
        } else {
            return i;//i本身未被移除，则返回自身
        }
    }

    //主函数
    public static void main(String[] args) {
        SuccessorWriteBySelf swbs = new SuccessorWriteBySelf(10);
        swbs.remove(7);
        System.out.println(swbs.getSuccssor(7));
        swbs.remove(8);
        swbs.remove(6);
        System.out.println(swbs.getSuccssor(6));
        swbs.remove(9);
        System.out.println(swbs.getSuccssor(6));
    }
}
