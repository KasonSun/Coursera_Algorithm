package com.algorithmI.secondWeek;

/**
 * 栈的数组实现
 */
public class StackArray {
    private String[] s;
    private int N = 0;


    public StackArray(int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        s[N++] = item;
    }

    public String pop() {
        String item = s[--N];
        s[N] = null;
        return item;
    }

    //测试
    public static void main(String[] args) {
        StackArray sa = new StackArray(2);
        sa.push("sun");
        System.out.println(sa.pop());
    }
}
