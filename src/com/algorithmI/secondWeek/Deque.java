package com.algorithmI.secondWeek;


import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 第二周编程作业 1.Deque
 * 操作要求：实现一个双端队列，可以在队头插入，也可以在队尾插入，可以在队头删除，也可以在队尾删除。
 * 时间要求：每次插入或删除的时间复杂度为常量。
 * 内存要求：一个包含n个对象的队列最多使用48n+192字节的内存。
 * 思路：要实现常量时间内的插入和删除，可以采用双向链表实现
 * ①队列的变量：长度，first头结点，last尾结点；
 * ②Node：Item和pre、next双向结点；
 * ③注意要实现Iterable<Item>接口中的hasNext、next、remove函数；
 * ④插入要考虑当第一个元素插入的情况:队头插入则记得修改队尾队尾插入则记得修改队头；删除也要考虑到只有一个元素的情况，队头删除记得修改队尾，队尾删除记得修改队头；
 * ⑤因为是双向链表，所以要记得更改与插入和删除元素相连接的结点，尤其要考虑到只有一个结点时的特殊情况；
 */
public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node first;
    private Node last;


    private class Node {
        private Item item;
        private Node pre;
        private Node next;
    }

    public Deque() {
        n = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    //头部添加
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Illege data");
        }
        Node temp = new Node();
        temp.item = item;
        temp.next = first;
        temp.pre = null;
        if (first != null) {
            first.pre = temp;
        }
        first = temp;
        n++;
        if (n == 1) {
            last = first;
        }
    }

    //尾部添加
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Illege data");
        }
        Node temp = new Node();
        temp.item = item;
        last = temp.pre;
        temp.next = null;
        if (last != null) {
            last.next = temp;
        }
        last = temp;
        n++;
        if (n == 1) {
            first = last;
        }
    }

    //头部删除
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        } else {
            first.pre = null;
        }
        return item;
    }

    //尾部删除
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Item item = last.item;
        last = last.pre;
        n--;
        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    //测试
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
        }
        for (int i = 10; i < 20; i++) {
            deque.addLast(i);
        }
        StdOut.println(deque.size());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.size());
    }


}
