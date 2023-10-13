package com.mmnaseri.cs.ctci.ch02.p04;

import com.mmnaseri.cs.ctci.ch02.Node;

public class ArrayListPartitioner {
    public static void main(String[] args) {
        Node head = new Node(20);
        head.setNext(new Node(40)).setNext(new Node(30)).setNext(new Node(50));
        System.out.println("head = " + head);
        Node partitioned = partition(head, 35);
        System.out.println("partitioned = " + partitioned);
        System.out.println("------------------------------");
        head = new Node(3);
        head.setNext(new Node(5)).
                setNext(new Node(8)).
                setNext(new Node(5)).
                setNext(new Node(10)).setNext(new Node(2)).setNext(new Node(1));
        System.out.println("head = " + head);
        partitioned = partition(head, 5);
        System.out.println("partitioned = " + partitioned);
    }

    public static Node partition(Node list, int value) {
        Node less = null;
        Node gt = null;
        Node current = list;
        while (current != null) {
            if (value > current.getValue()) {
                less = append(less, current.getValue());
            } else if (current.getValue() >= value) {
                gt = append(gt, current.getValue());
            }
            current = current.getNext();
        }
        return merge(less, gt);
    }

    private static Node append(Node list, int value) {
        if (list == null) {
            return new Node(value);
        } else {
            Node current = list;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Node(value));
        }
        return list;
    }

    private static Node merge(Node less, Node gt) {
        Node result = less;
        if (gt != null) {
            if (result == null) {
                result = gt;
            } else {
                Node current = result;
                while (current.getNext() != null) {
                    current = current.getNext();
                }
                current.setNext(gt);
            }
        }
        return result;
    }

}
