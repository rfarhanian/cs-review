package com.mmnaseri.cs.ctci.ch02.p07;

import com.mmnaseri.cs.ctci.ch02.Node;

public class IntersectionFinder {

    public static void main(String[] args) {
        Node first = new Node(1);
        first.setNext(new Node(2)).setNext(new Node(3));
        System.out.println("first = " + first);
        Node second = new Node(4);
        second.setNext(first);
        System.out.println("second = " + second);
        Node found = intersect(first, second);
        System.out.println("found = " + found);
        System.out.println("-----------------------------------------");
        first = new Node(1);
        first.setNext(new Node(2)).setNext(new Node(3));
        System.out.println("first = " + first);
        second = new Node(4);
        second.setNext(new Node(5));
        System.out.println("second = " + second);
        found = intersect(first, second);
        System.out.println("found = " + found);
        System.out.println("-----------------------------------------");
        first = new Node(1);
        Node last = new Node(4);
        first.setNext(new Node(2)).setNext(new Node(3)).setNext(last);
        System.out.println("first = " + first);
        second = new Node(6);
        second.setNext(last);
        System.out.println("second = " + second);
        found = intersect(first, second);
        System.out.println("found = " + found);
        System.out.println("-----------------------------------------");

    }

    public static Node intersect(Node first, Node second) {
        if (!hasIntersect(first, second)) {
            return null;
        }
        int firstLength = findLength(first);
        int secondLength = findLength(second);
        int delta = Math.abs(firstLength - secondLength);
        while (delta > 0) {
            if (firstLength > secondLength) {
                first = first.getNext();
            } else {
                second = second.getNext();
            }
            delta--;
        }
        while (first != null && second != null) {
            if (first == second) {
                return first;
            }
            first = first.getNext();
            second = second.getNext();
        }
        throw new IllegalStateException("There should be an intersection");
    }

    private static boolean hasIntersect(Node first, Node second) {
        if (first == null || second == null) {
            return false;
        }
        Node lastOfFirst = first;
        while (lastOfFirst.getNext() != null) {
            lastOfFirst = lastOfFirst.getNext();
        }
        Node lastOfSecond = second;
        while (lastOfSecond.getNext() != null) {
            lastOfSecond = lastOfSecond.getNext();
        }
        return lastOfFirst == lastOfSecond;
    }

    private static int findLength(Node node) {
        if (node == null) {
            return 0;
        }
        Node current = node;
        int count = 0;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
}
