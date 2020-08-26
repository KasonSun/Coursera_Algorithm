package com.algorithmI.secondWeek;

import java.util.Scanner;

/**
 * 队列的链表实现
 */
public class QueueLinklist<Item> {
    private Node first;
    private Node last;
    private int N;

    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return first==null;
    }

    public int size(){
        return N;
    }

    //入队
    public void enqueue(Item item){
        Node oldlast=last;
        last=new Node();
        last.item=item;
        last.next=null;
        if(isEmpty()){
            first=last;
        }else{
            oldlast.next=last;
        }
        N++;
    }

    //出队
    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if(isEmpty()){
            last=null;
        }
        N--;
        return item;
    }

    //测试
    public static void main(String[] args) {
        QueueLinklist<String> ql=new QueueLinklist<>();
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if(s!=null){
            ql.enqueue(s);
        }
        System.out.println("Queue is empty or not:"+ql.isEmpty());
        System.out.println("Queue size:"+ql.size());
        System.out.println("Queue content:"+ql.dequeue());

    }
}
