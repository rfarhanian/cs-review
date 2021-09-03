package com.mnaseri.cs.homework.ch10;

public abstract class AbstractLinkedList {

    private Node head;

    public void append(Object item) {
        Node node = new Node(item);
        if (head == null) {
            head = node;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = node;
    }

    public void prepend(Object item) {
        Node node = new Node(item);
        node.next = head;
        head = node;
    }

    public void deleteByValue(Object item) {
        if (head == null) {
            return;
        }
        if (head.data != null && head.data.equals(item) || (head.data == null && item == null)) {
            head = head.next;
            return;
        }
        Node current = head;
        while (current.next != null) {
            if ((current.next.data != null && current.next.data.equals(item)) || (current.next.data == null && item == null)) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (head == null) {
            return sb.append("Empty").toString();
        }
        sb.append(head.data);
        Node current = head.next;
        while (current != null) {
            sb.append(",").append(current.data);
            current = current.next;
        }
        return sb.toString();
    }

    protected static class Node {
        protected Object data;
        protected Node next;

        public Node(Object data) {
            this.data = data;
        }
    }
}
