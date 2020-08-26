package com.algorithmI.secondWeek;

/**
 * 栈：高效动态增长数组的大小（区别于一般的增长复制）
 */
public class StackResizeArray {
    private String[] s;
    private int N = 0;


    public StackResizeArray() {
        s = new String[1];
    }

    public boolean isEmpty() {
        return s.length == 0;
    }

    //动态增长数组大小
    public void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    public int getSize() {
        return s.length;
    }

    //当入栈元素达到数组的长度时，使数组长度增加一倍
    public void push(String item) {
        if (N == s.length) {
            resize(2 * s.length);
        }
        s[N++] = item;
    }

    //当出栈后元素为数组长度的1/4时，使数组长度变为原来的1/2
    public String pop() {
        String item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length / 4) {
            resize(s.length / 2);
        }

        return item;
    }

    //测试
    public static void main(String[] args) {
        StackResizeArray sra = new StackResizeArray();
        sra.push("sun");
        sra.push("sun");
        sra.push("sun");
        sra.push("sun");
        sra.push("sun");
        sra.push("sun");
        System.out.println("The array size after push stack：" + sra.getSize());
        sra.pop();
        sra.pop();
        sra.pop();
        sra.pop();
        System.out.println("The array size after pop stack：" + sra.getSize());

    }
}
