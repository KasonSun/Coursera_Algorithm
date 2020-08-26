package com.algorithmI.secondWeek;


import java.util.Scanner;

/**
 * 栈的链表实现
 */
public class StackLinklist<Item> {
    private Node first;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    //入栈
    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    //出栈
    public Item pop() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    //测试
    public static void main(String[] args) {
        StackLinklist<String> sl = new StackLinklist<>();
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if (s != null) {
            sl.push(s);
        }
        System.out.println("print stack whether is empty or not:" + sl.isEmpty());
        System.out.println("print stack size:" + sl.size());
        System.out.println("print stack:" + sl.pop());
    }
}
