package com.farhanian.cs.custom;

import java.util.Stack;

/**
 * You can also implement this data structure using a Stack<int[]> holding both min and current item at every step.
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/min-stack">The problem</a>
 * @see <a href="https://leetcode.com/problems/min-stack/description/">Leetcode</a>
 * @see <a href="https://leetcode.com/problems/max-stack/">You should also solve Max stack problem</a>
 */
class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> min;

    public MinStack() {
        stack = new Stack<>();
        min = new Stack<>();
    }

    public int pop() {
        min.pop();
        return stack.pop();
    }

    // Pushes values into MinStack
    public void push(Integer value) {
        stack.push(value);
        if (min.isEmpty() || value < min.peek()) {
            min.push(value);
        } else {
            min.push(min.peek());
        }
    }

    // returns minimum value in O(1)
    public int minNumber() {
        return min.peek();
    }
}