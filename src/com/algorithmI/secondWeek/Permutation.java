package com.algorithmI.secondWeek;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


/**
 * 第二周编程作业：3.Permutation
 * 操作要求：输入一组元素，插入到队列中，从命令行读取个数n，从中输出n个随机的元素。
 * 要求：不能利用额外的String数组来保存输入的String对象
 */
public class Permutation {
    public static void main(String[] args) {

        RandomizeQueue<String> rq=new RandomizeQueue<>();

        StdOut.print("please input number k is:");
        int k=Integer.parseInt(StdIn.readString());//输入的个数k
        StdOut.print("please output number n(n<=k) is:");
        int n = Integer.parseInt(StdIn.readString());//输出的个数n
        //输入
        StdOut.println("please input "+k+" Strings:");
        for (int i = 0; i < k; i++) {
            rq.enque(StdIn.readString());
        }
        //随机输出（n<=k才够输出）
        StdOut.print("Output "+ n +" Strings is:");
        if(n<=k){
            for(int i=0;i<n;i++){
                String temp = rq.deque();
                StdOut.print(temp+",");
            }
        }

    }
}
