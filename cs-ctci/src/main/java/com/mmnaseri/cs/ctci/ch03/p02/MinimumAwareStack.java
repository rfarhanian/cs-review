package com.mmnaseri.cs.ctci.ch03.p02;

public class MinimumAwareStack {

    private Node top = null;

    public static void main(String[] args) {
        MinimumAwareStack stack = new MinimumAwareStack();
        stack.push(6);
        stack.push(8);
        stack.push(2);
        stack.push(10);
        while (!stack.isEmpty()) {
            System.out.println("stack.min() = " + stack.min());
            System.out.println(stack.pop() + " popped.");
        }
    }

    public void push(int value) {
        int newMin;
        if (top == null) {
            newMin = value;
        } else {
            if (value < top.getMin()) {
                newMin = value;
            } else {
                newMin = top.getMin();
            }
        }
        Node newTop = new Node(value, newMin);
        newTop.setNext(top);
        top = newTop;
    }

    public int min() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        return top.getMin();
    }

    private boolean isEmpty() {
        return top == null;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        Node newHead = top.getNext();
        int result = top.getValue();
        top = newHead;
        return result;
    }

    private static class Node {
        private Node next;
        private Integer value;
        private Integer min;

        public Node(Integer value, Integer min) {
            this.value = value;
            this.min = min;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getMin() {
            return min;
        }

        public void setMin(Integer min) {
            this.min = min;
        }
    }
}
