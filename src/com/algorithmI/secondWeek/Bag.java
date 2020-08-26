package com.algorithmI.secondWeek;

import  java.util.Iterator;

/**
 *  背包 (bag)
 * 背包是一种不支持从中删除元素的集合数据类型，它的目的是收集元素并迭代遍历所有收集到的元素。使用背包说明元素的处理顺序不重要。
 *
 */
public class Bag<Item>  implements Iterable<Item> {
    private Node first;

    private class Node {
        Item item;
        Node next;
    }

    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {

        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
