package com.mmnaseri.cs.ctci.ch03.p03;

import java.util.Stack;

public class SetOfStacks {

    private Stack<Stack<Integer>> stacks = new Stack<>();
    private int stackCapacity, size = 0;
    public SetOfStacks(int stackCapacity) {
        this.stackCapacity = Math.min(2, stackCapacity);
    }

    public static void main(String[] args) {
        SetOfStacks stacks = new SetOfStacks(2);
        stacks.push(1);
        stacks.push(2);
        stacks.push(3);
        stacks.push(4);
        stacks.push(5);
        int poppedFromSecond = stacks.popAt(0);
        System.out.println(poppedFromSecond + " popped from second stack.");
        for (int i = 1; i < 5; i++) {
            int popped = stacks.pop();
            System.out.println(popped + " popped....");
        }
        System.out.println("stacks = " + stacks.isEmpty());
    }

    public void push(int value) {
        if (isEmpty() && stacks.isEmpty()) {
            stacks.push(new Stack<>());
        }
        Stack<Integer> current = stacks.peek();
        if (current.size() == this.stackCapacity) {
            stacks.push(new Stack<>());
        }
        current = stacks.peek();
        current.push(value);
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        int top = stacks.peek().pop();
        if (stacks.peek().isEmpty()) {
            stacks.pop();
        }
        size--;
        return top;
    }

    public int popAt(int index) {
        if (index < stacks.size()) {
            Stack<Integer> stack = stacks.get(index);
            Integer popped = stack.pop();
            if (stack.isEmpty()) {
                stacks.remove(stack);
            }
            size--;
            return popped;
        } else {
            throw new IllegalArgumentException("index is invalid");
        }

    }

    public boolean isEmpty() {
        return stacks.isEmpty() || stacks.peek().isEmpty();
    }
}
