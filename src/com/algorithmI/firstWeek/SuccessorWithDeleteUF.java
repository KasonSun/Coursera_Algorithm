package com.algorithmI.firstWeek;

/**
 * 第一周编程作业第三天：从S集合（0-N-1整数）移除x，并使用getSuccessor()方法获取剩余元素集合中y>=x的最小数
 */

public class SuccessorWithDeleteUF {
    private int num;
    private int[] id;
    private boolean[] isRemove;

    public SuccessorWithDeleteUF(int n){
        num = n;
        id = new int[n];
        isRemove = new boolean[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            isRemove[i] = false;
        }
    }

    public int root(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }

    public void union(int p, int q) {
        //此处的union取较大根
        int pRoot = root(p);
        int qRoot = root(q);
        if (pRoot == qRoot)
            return;
        else if (pRoot < qRoot)
            id[pRoot] = qRoot;
        else
            id[qRoot] = pRoot;
    }

    public void remove(int x) {
        isRemove[x] = true;
        //判断相邻节点是否也被remove掉了，如果remove掉就union
        if (x>0 && isRemove[x-1]){
            union(x,x-1);
        }
        if (x<num-1 && isRemove[x+1]){
            union(x,x+1);
        }
    }

    public int getSuccessor(int x) {
        if(x<0 || x>num-1){//越界异常
            throw new IllegalArgumentException("访问越界!");
        }else if(isRemove[x]){
            if(root(x)+1 > num-1) //x以及大于x的数都被remove掉了，返回-1
                return -1;
            else //所有remove数集中最大值+1，就是successor
                return root(x)+1;
        }else {//x未被remove，就返回x自身
            return x;
        }
    }

    public static void main(String[] args) {
        SuccessorWithDeleteUF swd = new SuccessorWithDeleteUF(10);
        swd.remove(7);
        swd.remove(8);
        swd.remove(6);
        System.out.println("the successor is : " + swd.getSuccessor(6));
        swd.remove(7);
        swd.remove(9);
        System.out.println("the successor is : " + swd.getSuccessor(9));
    }
}