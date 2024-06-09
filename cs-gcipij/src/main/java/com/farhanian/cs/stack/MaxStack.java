package com.farhanian.cs.stack;

import java.util.*;

/**
 * A simplified version of this problem is already part of the Grokking Coding Interview Patterns In Java
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/min-stack">A simplified problem</a>
 * @see <a href="https://leetcode.com/problems/max-stack/">Leetcode problem</a>
 */
public class MaxStack {
    private PriorityQueue<int[]> pq;
    private Stack<int[]> stack;
    private Set<Integer> removed = new HashSet<>(); //count
    private int count = 0;

    public MaxStack() {
        pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                int aIndex = a[1];
                int bIndex = b[1];
                int aValue = a[0];
                int bValue = b[0];
                if (aValue == bValue) {
                    return bIndex - aIndex;
                } else {
                    return bValue - aValue;
                }
            }
        });
        stack = new Stack<>();
    }

    public void push(int x) {
        pq.add(new int[]{x, count});
        stack.push(new int[]{x, count});
        count++;
    }

    public int pop() {
        while (removed.contains(stack.peek()[1])) {
            stack.pop();
        }
        int[] candidate = stack.pop();
        removed.add(candidate[1]);
        return candidate[0];
    }

    public int top() {
        while (removed.contains(stack.peek()[1])) {
            stack.pop();
        }
        return stack.peek()[0];
    }

    public int peekMax() {
        while (!pq.isEmpty() && removed.contains(pq.peek()[1])) {
            pq.remove();
        }
        return pq.peek()[0];
    }

    public int popMax() {
        peekMax();
        int[] candidate = pq.remove();
        removed.add(candidate[1]);
        return candidate[0];
    }
}