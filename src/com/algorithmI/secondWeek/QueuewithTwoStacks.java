package com.algorithmI.secondWeek;

import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;

/**
 *  Interview Questions: Queue with two stacks
 *  要求用栈实现队列的操作
 */
public class QueuewithTwoStacks<Item> {
    StackLinklist inSra=new StackLinklist();
    StackLinklist outSra=new StackLinklist();

    public int size(){
        return inSra.size()+outSra.size();
    }

    public boolean isEmpty(){
        if((inSra.isEmpty())&&(outSra.isEmpty())){
            return true;
        }
        return false;
    }

    public void enqueue(Item item){
        inSra.push(item);
    }

    public Item dequeue(){
        if(outSra.isEmpty()){
            if(inSra.isEmpty()){
                return null;
            }else{
                while(!inSra.isEmpty()){
                    outSra.push(inSra.pop());
                }
                return (Item)outSra.pop();
            }
        }
        return (Item)outSra.pop();
    }


    //测试
    public static void main(String[] args) {
        QueuewithTwoStacks<Integer> qts=new QueuewithTwoStacks<>();
        qts.enqueue(10);
        qts.enqueue(9);
        qts.enqueue(8);
        System.out.println("Queue size is:"+qts.size());
        System.out.println("Queue is empty or not:"+qts.isEmpty());
        System.out.println("Dequeue element is:"+qts.dequeue());
    }
}
