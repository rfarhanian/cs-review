package com.mnaseri.cs.homework.ch10;

public class DoublyLinkedList extends AbstractLinkedList {

    private Node head;

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.append(4);
        list.prepend(3);
        list.prepend(2);
        list.prepend(1);
        System.out.println("list.toString() = " + list);
        System.out.println("reverse() = " + list.reverse());
        list.deleteByValue(4);
        System.out.println("list.deleteByValue(4)");
        System.out.println("list.toString() = " + list);
        System.out.println("reverse() = " + list.reverse());
        list.deleteByValue(1);
        System.out.println("list.deleteByValue(1); = ");
        System.out.println("list.toString() = " + list);
        System.out.println("reverse() = " + list.reverse());
        list.deleteByValue(3);
        System.out.println("list.deleteByValue(3)");
        list.deleteByValue(2);
        System.out.println("list.deleteByValue(2)");
        System.out.println("list.toString() = " + list);
        System.out.println("reverse() = " + list.reverse());

    }

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
        node.prev = current;
        current.next = node;
    }

    public void prepend(Object item) {
        Node node = new Node(item);
        head.prev = node;
        node.next = head;
        head = node;
    }

    public void deleteByValue(Object item) {
        if (head == null) {
            return;
        }
        if (head.data != null && head.data.equals(item) || (head.data == null && item == null)) {
            if (head.next != null) {
                head.next.prev = null;
            }
            head = head.next;
            return;
        }
        Node current = head.next;
        while (current != null) {
            if ((current.data != null && current.data.equals(item)) || (current.data == null && item == null)) {
                Node newNext = current.next;
                Node prev = current.prev;
                //prev work
                if (prev != null) {
                    prev.next = newNext;
                }
                //newNext work
                if (newNext != null) {
                    newNext.prev = prev;
                }
                //current work
                current.next = null;
                current.prev = null;
                return;
            }
            current = current.next;
        }
    }

    public String reverse() {
        StringBuilder sb = new StringBuilder();
        if (head == null) {
            return sb.append("Empty").toString();
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        sb.append(current.data);
        current = current.prev;
        while (current != null) {
            sb.append(",").append(current.data);
            current = current.prev;
        }
        return sb.toString();
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

    private static class Node {
        private Node prev;
        private Node next;
        private Object data;

        public Node(Object data) {
            this.data = data;
        }
    }
}
