package com.mmnaseri.cs.ctci.ch02;

public class Node {
    private int value;
    private Node next;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public Node setNext(Node next) {
        this.next = next;
        return this.next;
    }

    @Override
    public String toString() {
        return "(value:" + value + (next != null ? ", ->\n" + next : "") +
                ")";
    }
}
