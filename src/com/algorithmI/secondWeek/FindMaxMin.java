package com.algorithmI.secondWeek;


import java.util.Stack;

/**
 * Interview Questions:StackWithMax
 * 在实现栈的功能的同时能够返回当前栈中的最大值
 */
public class FindMaxMin extends Stack {

    private Stack minStack;
    private Stack maxStack;
    private Stack store;

    public FindMaxMin() {
        minStack = new Stack();
        maxStack = new Stack();
        store = new Stack();
    }

    public void push(int value) {

        if (value >= MaxValue()) { //note the sign "=";
            maxStack.push(value);
        }
        if (value <= MinValue()) {
            minStack.push(value);
        }
        store.push(value);

    }

    public Integer pop() {

        int value = (int) store.pop();
        if (value == MaxValue()) {
            maxStack.pop();
        }
        if (value == MinValue()) {
            minStack.pop();
        }
        return value;
    }

    public int MaxValue() {
        if (maxStack.isEmpty()) {
            return Integer.MIN_VALUE;
        } else {
            return (int) maxStack.peek();//return but not remove  the most recently added element
        }

    }

    public int MinValue() {
        if (minStack.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return (int) minStack.peek();
        }

    }

    /**
     * 本地测试
     *
     * @param args
     */
    public static void main(String args[]) {
        FindMaxMin test = new FindMaxMin();
        System.out.println("the biggest is " + test.MaxValue() + " and the smallest is " + test.MinValue());
        test.push(9);
        test.pop();
        test.push(5);
        test.push(3);
        test.push(7);
        test.push(1);
        test.pop();
        System.out.println("the biggest is " + test.MaxValue() + " and the smallest is " + test.MinValue());

    }

}
