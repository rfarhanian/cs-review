package com.mnaseri.cs.homework.ch10;

/**
 * Stack has two cases to consider:
 * 1-The top is null.
 * 3-There is one or more items in the stack.
 */
public class Stack {

    private Node top;

    public static void main(String[] args) {
        Stack s = new Stack();
        s.push(4);
        s.push(3);
        s.push(2);
        s.push(1);
        System.out.println("s = " + s);
        System.out.println("pop = " + s.pop());
        System.out.println("s = " + s);
        System.out.println("pop = " + s.pop());
        System.out.println("s = " + s);
        System.out.println("pop = " + s.pop());
        System.out.println("s = " + s);
        System.out.println("pop = " + s.pop());
        System.out.println("s = " + s);
        System.out.println("pop = " + s.pop());
        System.out.println("s = " + s);

    }

    public void push(Integer value) {
        Node node = new Node(value);
        if (top == null) {
            top = node;
            return;
        }
        node.next = top;
        top = node;
    }

    public Integer pop() {
        if (top == null) {
            return null;
        }
        Integer value = top.data;
        top = top.next;
        return value;
    }

    public Integer peek() {
        return top != null ? top.data : null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (top == null) {
            return sb.append("Empty").toString();
        }
        Node current = top.next;
        sb.append(top.data);
        while (current != null) {
            sb.append(",").append(current.data);
            current = current.next;
        }
        return sb.toString();
    }

    private static class Node {
        private Node next;
        private Integer data;

        public Node(int data) {
            this.data = data;
        }
    }
}
