package com.mmnaseri.cs.ctci.ch03.p04;

import java.util.Stack;

public class MyQueue {


    private Stack<Integer> newest = new Stack<>();
    private Stack<Integer> oldest = new Stack<>();

    public void queue(int value) {
        int last = value;
        oldest.push(last);
        newest = reverse(oldest);
    }

    private Stack<Integer> reverse(Stack<Integer> stack) {
        Stack<Integer> copy = new Stack<>();
        copy.addAll(stack);
        Stack<Integer> reversed = new Stack<>();
        while (!copy.isEmpty()) {
            reversed.push(copy.pop());
        }
        return reversed;
    }

    public int dequeue() {
        int first = newest.pop();
        oldest = reverse(newest);
        return first;
    }

    public int peek() {
        return newest.peek();
    }
}
