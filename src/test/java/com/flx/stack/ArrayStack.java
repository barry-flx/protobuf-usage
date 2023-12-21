package com.flx.stack;

import java.util.Arrays;

public class ArrayStack {

    private String[] items;

    private int count;

    private int n;

    public ArrayStack(int n) {
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    public void push(String item) {
        if (count == n) {
            expand();
        }
        items[count] = item;
        ++count;
    }

    public String pop() {
        if (count == 0) {
            return null;
        }
        String temp = items[count - 1];
        items[count - 1] = null;
        --count;
        return temp;
    }

    private void expand() {
        n = n * 2;
        String[] newItems = new String[n];
        System.arraycopy(items, 0, newItems, 0, count);
        items = newItems;
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(2);
        stack.push("A1");
        stack.push("A3");
        stack.push("A2");
        stack.push("A5");
        stack.push("A9");
        stack.push("A7");
        stack.push("A6");
        System.out.println(Arrays.toString(stack.items));
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(Arrays.toString(stack.items));
    }

}
