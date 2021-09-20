package com.mnaseri.cs.homework.ctci.ch03;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solutions {

    public static void main(String[] args) {
        ThreeInOne threeInOne = new ThreeInOne(3, 8);
        threeInOne.push(1, 1);
        threeInOne.push(1, 5);
        threeInOne.push(2, 2);
        threeInOne.push(2, 6);
        threeInOne.push(3, 3);
        threeInOne.push(3, 7);
        System.out.println("threeInOne.pop(1) = " + threeInOne.pop(1));
        System.out.println("threeInOne.peek(1) = " + threeInOne.peek(1));
        threeInOne.push(2, 8);
        System.out.println("threeInOne.pop(3) = " + threeInOne.pop(3));
        StackMin stackMin = new StackMin();
        stackMin.push(150);
        System.out.println("stackMin.min() = " + stackMin.min());
        stackMin.push(5);
        System.out.println("stackMin.min() = " + stackMin.min());
        stackMin.push(101);
        System.out.println("stackMin.min() = " + stackMin.min());
        stackMin.push(2);
        System.out.println("stackMin.min() = " + stackMin.min());
        stackMin.push(10);
        System.out.println("stackMin.min() = " + stackMin.min());
        System.out.println("stackMin.pop() = " + stackMin.pop());
        System.out.println("stackMin.min() = " + stackMin.min());
        System.out.println("stackMin.pop() = " + stackMin.pop());
        System.out.println("stackMin.min() = " + stackMin.min());
        System.out.println("stackMin.pop() = " + stackMin.pop());
        System.out.println("stackMin.min() = " + stackMin.min());
        System.out.println("stackMin.pop() = " + stackMin.pop());
        System.out.println("stackMin.min() = " + stackMin.min());

        System.out.println("-----------------------------------");
        StackMin2 stackMin2 = new StackMin2();
        stackMin2.push(150);
        System.out.println("stackMin2.min() = " + stackMin2.min());
        stackMin2.push(5);
        System.out.println("stackMin2.min() = " + stackMin2.min());
        stackMin2.push(101);
        System.out.println("stackMin2.min() = " + stackMin2.min());
        stackMin2.push(2);
        System.out.println("stackMin2.min() = " + stackMin2.min());
        stackMin2.push(10);
        System.out.println("stackMin2.min() = " + stackMin2.min());
        System.out.println("stackMin2.pop() = " + stackMin2.pop());
        System.out.println("stackMin2.min() = " + stackMin2.min());
        System.out.println("stackMin2.pop() = " + stackMin2.pop());
        System.out.println("stackMin2.min() = " + stackMin2.min());
        System.out.println("stackMin2.pop() = " + stackMin2.pop());
        System.out.println("stackMin2.min() = " + stackMin2.min());
        System.out.println("stackMin2.pop() = " + stackMin2.pop());
        System.out.println("stackMin2.min() = " + stackMin2.min());
        System.out.println("-----------------------------------");
        StackOfPlates stackOfPlates = new StackOfPlates(4);
        stackOfPlates.push(8);
        stackOfPlates.push(14);
        stackOfPlates.push(150);
        stackOfPlates.push(5);
        stackOfPlates.push(6);

        System.out.println("stackOfPlates.pop(0) = " + stackOfPlates.popAt(0));
        System.out.println("stackOfPlates.pop(0) = " + stackOfPlates.popAt(0));
        System.out.println("stackOfPlates.pop(0) = " + stackOfPlates.popAt(0));
        System.out.println("stackOfPlates.pop(0) = " + stackOfPlates.popAt(0));
        System.out.println("stackOfPlates.pop() = " + stackOfPlates.pop());

    }

    public static class ThreeInOne {

        private int[] data;
        private int[] stackIndex;
        private int capacity;


        public ThreeInOne(int numberOfStacks, int capacity) {
            data = new int[numberOfStacks * capacity];
            stackIndex = new int[numberOfStacks];
            this.capacity = capacity;
        }

        public int peek(int stackNo) {
            if (isEmpty(stackNo)) {
                throw new IllegalStateException("Stack " + stackNo + " is empty.");
            }
            int topIndex = topIndex(stackNo) - 1;
            return data[topIndex];
        }

        private int topIndex(int stackNo) {
            int start = (stackNo - 1) * capacity;
            return start + stackIndex[stackNo - 1];
        }

        public void push(int stackNo, int value) {
            if (isFull(stackNo)) {
                throw new IllegalStateException("stack " + stackNo + " is full.");
            }
            int topIndex = topIndex(stackNo);
            data[topIndex] = value;
            stackIndex[stackNo - 1]++;
        }

        private boolean isFull(int stackNo) {
            return stackIndex[stackNo - 1] == capacity;
        }

        private boolean isEmpty(int stackNo) {
            return stackIndex[stackNo - 1] == 0;
        }

        public int pop(int stackNo) {
            if (isEmpty(stackNo)) {
                throw new IllegalStateException("Stack " + stackNo + " is already empty.");
            }
            int topIndex = topIndex(stackNo) - 1;
            stackIndex[stackNo - 1]--;
            int value = data[topIndex];
            data[topIndex] = -1;
            return value;
        }

        //push, pop, peek
    }

    public static class StackMin {


        private Node top;

        public void push(int value) {
            int prev = top != null ? top.value : value;
            Node newNode = new Node(value, prev);
            newNode.next = top;
            top = newNode;
        }

        public int pop() {
            if (top == null) {
                throw new IllegalStateException("Stack is empty.");
            }
            Node oldTop = top;
            Node newTop = oldTop.next;
            int value = oldTop.value;
            top = newTop;
            return value;
        }

        public int min() {
            if (top == null) {
                throw new IllegalStateException("Empty Stack does have a minimum.");
            }
            return top.min;
        }

        private static class Node {
            private Node next;
            private int value;
            private int min;

            public Node(int value, int prev) {
                this.value = value;
                this.min = Math.min(prev, this.value);
            }
        }
    }

    public static class StackMin2 {


        private Node top;
        private Stack<Integer> min = new Stack<>();


        public void push(int value) {
            Node newNode = new Node(value);
            newNode.next = top;
            if (min.isEmpty()) {
                min.push(value);
            } else if (min.peek() >= newNode.value) {
                min.push(value);
            }

            top = newNode;

        }

        public int pop() {
            if (top == null) {
                throw new IllegalStateException("Stack is empty.");
            }
            Node oldTop = top;
            Node newTop = oldTop.next;
            int value = oldTop.value;
            if (oldTop.value == min.peek()) {
                min.pop();
            }
            top = newTop;
            return value;
        }

        public int peek() {
            if (top == null) {
                throw new IllegalStateException("Stack is empty.");
            }
            return top.value;
        }

        public int min() {
            if (top == null) {
                throw new IllegalStateException("Empty Stack does have a minimum.");
            }
            return min.peek();
        }

        private static class Node {
            private Node next;
            private int value;


            public Node(int value) {
                this.value = value;
            }
        }
    }

    public static class StackOfPlates {

        private List<Stack<Integer>> stacks = new ArrayList<>();
        private int capacity;
        private int currentStack = -1;

        public StackOfPlates(int capacity) {
            this.capacity = capacity;
        }

        private void addStack() {
            currentStack++;
            stacks.add(new Stack<Integer>());
        }

        private boolean noStack() {
            return stacks.isEmpty();
        }

        private void removeStack() {
            stacks.remove(currentStack);
            currentStack--;
        }

        private Stack<Integer> getCurrentStack() {
            return stacks.get(this.currentStack);
        }

        private boolean exceedsLimit(Stack<Integer> stack) {
            return stack.size() == this.capacity;
        }

        private boolean isEmpty() {
            Stack<Integer> stack = getCurrentStack();
            return stack.isEmpty();
        }

        //push, pop, peek

        public void push(int value) {
            if (noStack() || exceedsLimit(getCurrentStack())) {
                addStack();
            }
            Stack<Integer> stack = getCurrentStack();
            stack.push(value);
        }

        public int pop() {
            if (noStack()) {
                throw new IllegalStateException("Stack cannot be poped.");
            }
            Stack<Integer> stack = getCurrentStack();
            int result = stack.pop();
            if (isEmpty()) {
                removeStack();
            }
            return result;
        }

        public int popAt(int index) {
            Stack<Integer> stack = stacks.get(index);
            int value = stack.pop();
            if (stack.isEmpty()) {
                stacks.remove(index);
                currentStack = stacks.size() - 1;
            }
            return value;
        }

        // private void shift(int index) {
        // 	Stack<Integer> stack = stacks.get(index);
        // 	Stack<Integer> another= new Stack<>();
        // 	while(currentStack>index) {
        // 		another.push(pop());
        // 	}
        // 	while(!another.isEmpty()){
        // 		push(another.pop());
        // 	}
        // }

        public int peek() {
            if (noStack()) {
                throw new IllegalStateException("Stack cannot be peeked.");
            }
            Stack<Integer> stack = getCurrentStack();
            return stack.peek();
        }


    }
}
