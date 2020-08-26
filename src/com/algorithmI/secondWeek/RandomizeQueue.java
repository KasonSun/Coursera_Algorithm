package com.algorithmI.secondWeek;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 第二周编程作业 2.RandomizeQueue
 * 操作要求：按顺序的进行插入元素，但是随机地删除队列中的一个元素，迭代器也要求随机地访问所有元素；
 * 性能要求：删除队列中的一个元素，均摊为常量时间；
 * 内存要求：n个元素的队列，48n+192字节内存空间；
 * 思路：采用动态数组来实现随机队列，随机队列可以随机地删除一个元素，首先产生一个均匀分布的在[0, N)之间的随机数，
 * 并与items[N-1]交换，然后弹出items[N-1]；其迭代器也要求随机遍历元素，实现方式是取出数组下标构成一个index[]数组，
 * 同样用StdRandom对index[]进行随机洗牌，然后按照产生的随机数组下标遍历容器中的元素。
 * 注释：
 * 特别注意，Java对可存放任意数据类型数组的声明使不支持的，因此我们需要运用cast
 * 例如：
 * array=(Item[]) new Object[size]
 * <p>
 * 总结
 * ① items = (Item[]) new Object[1];会报警告（是因为[Java]不允许创建泛型数组（某些历史和技术原因）
 * 所以泛型数组只能通过强制类型转换(Item[]) new Object[cap]）实现。因此出现的警告不可避免，忽略即可。
 * ②插入元素前判断是否需要扩容，删除元素后判断是否需要缩减容量；
 * ③尤其注意迭代器的实现，利用了一个额外的index数组，来实现随机排列；
 */
public class RandomizeQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] items;

    public RandomizeQueue() {
        n = 0;
        items = (Item[]) new Object[1];//看上面注释（实际就是java数组是不支持泛型的）
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void resize(int length) {
        Item[] temp = (Item[]) new Object[length];
        for (int i = 0; i < n; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    //顺序入队
    public void enque(Item item) {
        if (n == items.length) {
            resize(2 * items.length);
        }
        if (item == null) {
            throw new IllegalArgumentException("Illege data");
        }
        items[n++] = item;
    }

    //随机交换
    public void randomSwap() {
        if (n <= 1) {
            return;
        }
        int random = StdRandom.uniform(n);
        Item temp = items[random];
        items[random] = items[n - 1];
        items[n - 1] = temp;
    }

    //随机出队(实际将n-1位置元素出队)
    public Item deque() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        randomSwap();
        Item item = items[n - 1];
        items[n - 1] = null;
        n--;
        if (n > 0 && n == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    //随机返回一个元素但是不移除他
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        randomSwap();
        return items[n - 1];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        private final int[] index;
        private int current;

        public RandomQueueIterator() {
            index = new int[n];
            for (int i = 0; i < n; i++) {
                index[i] = i;
            }
            for (int i = n - 1; i >= 0; i--) {
                int random = StdRandom.uniform(i + 1);
                int temp = index[random];
                index[random] = index[i];
                index[i] = temp;
            }
            current = n - 1;
        }

        @Override
        public boolean hasNext() {
            return current >= 0;
        }

        @Override
        public Item next() {
            if (current < 0) {
                throw new NoSuchElementException("Deque is empty");
            }
            return items[index[current--]];
        }

        public void remove() {
            throw new UnsupportedOperationException("Operation is error");
        }
    }

    //测试
    public static void main(String[] args) {
        RandomizeQueue<String> rq = new RandomizeQueue<>();
        String[] test = {"sun", "xue", "kui"};
        for (int i = 0; i < test.length; i++) {
            rq.enque(test[i]);
        }

        StdOut.print("Current RandomQueue is(for):");
        for (String s : rq) {
            StdOut.print(s + ",");
        }

        StdOut.println();
        Iterator<String> iterator = rq.iterator();
        StdOut.print("Current RandomQueue is(Iterator):");
        while (iterator.hasNext()) {
            StdOut.print(iterator.next() + ",");
        }

        StdOut.println();
        String w = rq.deque();
        StdOut.println("Random deque:" + w);

    }
}
