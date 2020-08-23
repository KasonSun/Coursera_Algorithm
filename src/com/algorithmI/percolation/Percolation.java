package com.algorithmI.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Percolation类
 * 有一个N*N矩阵，如图，每个小格子代表一个site，当site为black时说明当前site为blocked(关闭的),非黑色为open,
 * 当一个site为open直接可以连接到顶部或者该site可以通过其他open的site间接连接到顶部则称这个site为full，
 * 如果矩阵最底部有site可以连接到矩阵顶部，我们称这个矩阵为渗透的。
 */

public class Percolation {
    private boolean [] siteStatus; //存储节点是否开放
    private  int n; //方阵的行列数
    private int numOpenSites; //开放节点个数
    private WeightedQuickUnionUF topBotWeightedQuickUnionUF;   //连接虚头、虚尾节点的QUF
    private WeightedQuickUnionUF topWeightedQuickUnionUF;  //仅含虚头的QUF
    private int virtualTop;  //头部虚节点
    private int virtualBot;   //底部虚节点

    public Percolation(int n)
    {
        numOpenSites = 0;
        if (n <= 0)
        {
            throw new java.lang.IllegalArgumentException("illege data");
        }
        else
        {
            this.n = n;
            virtualTop = 0; //头部虚节点
            virtualBot = n*n +1; //底部虚节点

            siteStatus = new boolean [n*n +2];
            siteStatus[virtualTop] = true;
            siteStatus[virtualBot] = true;

            topBotWeightedQuickUnionUF = new WeightedQuickUnionUF(n*n +2);
            topWeightedQuickUnionUF = new WeightedQuickUnionUF(n*n+1);
        }
    }
    private int rowCol(int row, int col) {  //获取（row，col）在数组siteStatus中的位置
        if (row < 1 || row > n || col < 1 || col > n)
        {
            throw new java.lang.IllegalArgumentException("The scope of is row or colw is wrong");
        }
        return (row-1)*n + col;
    }

    public void open(int row, int col)
    {
        if (!isOpen(row, col))
        {
            int rowcol = rowCol(row, col);
            siteStatus[(row-1)*n +col] = true;
            numOpenSites++;
            if (row == 1)   //第一行元素与虚头节点相连
            {
                topBotWeightedQuickUnionUF.union(rowcol, virtualTop);
                topWeightedQuickUnionUF.union(rowcol, virtualTop);
            }
            if (row == n) //最后一行元素与虚尾节点相连
            {
                topBotWeightedQuickUnionUF.union(rowcol, virtualBot);
            }

            //以当期节点的上下左右开放性来进行合并（上下以行为基准，左右以列为基准）
            //上
            if (row > 1 && isOpen(row-1, col)) {
                topBotWeightedQuickUnionUF.union(rowcol, rowCol(row-1, col));
                topWeightedQuickUnionUF.union(rowcol, rowCol(row-1, col));
            }
            //下
            if (row < n && isOpen(row+1, col)) {
                topBotWeightedQuickUnionUF.union(rowcol, rowCol(row+1, col));
                topWeightedQuickUnionUF.union(rowcol, rowCol(row+1, col));
            }
            //左
            if (col > 1 && isOpen(row, col-1)) {
                topBotWeightedQuickUnionUF.union(rowcol, rowCol(row, col-1));
                topWeightedQuickUnionUF.union(rowcol, rowCol(row, col-1));
            }
            //右
            if (col < n && isOpen(row, col+1)) {
                topBotWeightedQuickUnionUF.union(rowcol, rowCol(row, col+1));
                topWeightedQuickUnionUF.union(rowcol, rowCol(row, col+1));
            }
        }

    }

    public boolean isOpen(int row, int col)   //是否为开放态
    {
        return siteStatus[rowCol(row, col)];
    }

    public boolean isFull(int row, int col)  //是否为联通态
    {
        return topWeightedQuickUnionUF.connected(rowCol(row, col), virtualTop);

    }

    public  int numberOfOpenSites()      //计算开放节点数量
    {
        return numOpenSites;
    }

    public boolean percolates()           //是否渗透
    {
        return topBotWeightedQuickUnionUF.connected(virtualTop, virtualBot);

    }

    public static void main(String[] args)   // test client (optional)
    {

        int nn = 5;
        Percolation myPercolation = new Percolation(nn);
        myPercolation.open(1, 1);
        myPercolation.open(1, 3);
        myPercolation.open(3, 4);
        myPercolation.open(2, 2);
        myPercolation.open(2, 4);
        System.out.println(myPercolation.percolates());
        System.out.println(myPercolation.numberOfOpenSites());
        myPercolation.open(3, 5);
        myPercolation.open(5, 5);
        myPercolation.open(4, 5);
        myPercolation.open(4, 3);
        myPercolation.open(4, 1);
        myPercolation.open(2, 3);
        System.out.println(myPercolation.percolates());
        System.out.println(myPercolation.numberOfOpenSites());
        myPercolation.open(5, 3);
        myPercolation.open(5, 2);
        myPercolation.open(5, 1);
        myPercolation.open(3, 1);
        System.out.println(myPercolation.percolates());
        System.out.println(myPercolation.numberOfOpenSites());
    }
}




