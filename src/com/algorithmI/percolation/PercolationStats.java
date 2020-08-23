package com.algorithmI.percolation;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * PercolationStats类
 *  渗透率：渗透时开放节点数与总结点数的比值
 * 利用方差和平均值思想找出置信率为95%的阀值
 *
 */

public class PercolationStats  {
    private final double []arryp;  //记录p值
    private final int trails; //实验次数
    public PercolationStats(int n, int trials)
    {
        Percolation testPercolation;
        if (n < 1 || trials < 1) {
            throw new java.lang.IllegalArgumentException("The scope of is n or trials is wrong");
        }
        this.trails = trials;
        arryp = new double[trials];
        for (int i = 0; i < trials; i++) {    //trials次随机测试
            testPercolation = new Percolation(n);
            while (!testPercolation.percolates()) {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                testPercolation.open(row, col);
            }
            int nunOS = testPercolation.numberOfOpenSites();
            arryp[i] = (double) nunOS/(n*n);  //记录p值
        }

    }

    public double mean()      //计算p值的平均数
    {
        return StdStats.mean(arryp);
    }
    public double stddev()     //计算p值的标准偏差
    {
        return StdStats.stddev(arryp);

    }
    public double confidenceLo()       //可信度最低范围
    {

        return mean()-1.96*stddev()/Math.sqrt(trails);

    }
    public double confidenceHi()      //可信度最高范围
    {

        return mean()+1.96*stddev()/Math.sqrt(trails);
    }


    public static void main(String[] args)
    {
        int n = 10, trialNum = 1000;
        PercolationStats ps = new PercolationStats(n, trialNum);
        StdOut.println("grid size :" + n+"*"+n);
        StdOut.println("trial times :" + trialNum);
        StdOut.println("mean of p :"+ ps.mean());
        StdOut.println("standard deviation :"+ps.stddev());
        StdOut.println("confidence interval low :"+ps.confidenceLo());
        StdOut.println("confidence interval high :"+ps.confidenceHi());
    }

}

