package com.mnaseri.cs.homework.ch10;

/**
 * Queue has three cases to consider:
 * 1-The head is null.
 * 2-There is only one item in the queue.
 * 3-There are more than one item in the queue.
 */
public class Queue {

    private Node head, tail;

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue(1);
        System.out.println("queue.dequeue() = " + queue.dequeue());
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println("queue.dequeue() = " + queue.dequeue());
        System.out.println("queue.dequeue() = " + queue.dequeue());
        try {
            queue.dequeue();
        } catch (Exception e) {
            System.out.println("queue is already empty.");
        }
        queue.enqueue(null);
        System.out.println("queue.dequeue() = " + queue.dequeue());
    }

    public void enqueue(Object x) {
        Node n = new Node(x);
        System.out.println("enqueue " + x);
        if (tail != null) {
            tail.next = n;
        }
        tail = n;
        if (head == null) {
            head = n;
        }
    }

    public Object dequeue() {
        if (head == null) {
            throw new IllegalArgumentException("Queue is empty");
        }
        Object data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return data;
    }

    public Object peek() {
        if (head == null) {
            return null;
        }
        return head.data;
    }

    private static class Node {
        private Node next;
        private Object data;

        public Node(Object data) {
            this.data = data;
        }
    }
}
