package com.mmnaseri.cs.ctci.ch03.p05;

import java.util.Stack;

public class StackSorter {

    public static void main(String[] args) {
        StackSorter stackSorter = new StackSorter();
        Stack<Integer> input = new Stack<>();
        input.push(10);
        input.push(15);
        input.push(11);
        input.push(1);
        input.push(5);
        System.out.println("input = " + input);
        stackSorter.sortWithTwoStacks(input);
        System.out.println("output = " + input);
    }

    public void sortWithTwoStacks(Stack<Integer> input) {
        if (input == null) {
            return;
        }
        Stack<Integer> sorted = new Stack<>();
        sorted.push(input.pop());
        while (!input.isEmpty()) {
            Integer candidate = input.pop();
            while (!sorted.isEmpty() && candidate < sorted.peek()) {
                input.push(sorted.pop());
            }
            sorted.push(candidate);
        }
        input.addAll(sorted);
    }

    public void sortWithThreeStacks(Stack<Integer> input) {
        if (input == null) {
            return;
        }
        Stack<Integer> result = new Stack<>();
        while (!input.isEmpty()) {
            Integer candidate = findMin(input);
            result.push(candidate);
        }
        input.addAll(result);
    }

    private int findMin(Stack<Integer> input) {
        Stack<Integer> temp = new Stack<>();
        int min = input.pop();
        while (!input.isEmpty()) {
            Integer candidate = input.pop();
            if (min > candidate) {
                temp.push(min);
                min = candidate;
            } else {
                temp.push(candidate);
            }
        }
        while (!temp.isEmpty()) {
            input.push(temp.pop());
        }
        return min;
    }

}
