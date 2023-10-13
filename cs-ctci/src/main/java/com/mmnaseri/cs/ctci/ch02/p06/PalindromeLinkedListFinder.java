package com.mmnaseri.cs.ctci.ch02.p06;

import com.mmnaseri.cs.ctci.ch02.Node;

import java.util.Objects;

public class PalindromeLinkedListFinder {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.setNext(new Node(2)).setNext(new Node(1));
        System.out.println("head = " + head);
        boolean isPalindrome = isPalindrome(head);
        System.out.println("isPalindrome = " + isPalindrome);
        System.out.println("-------------------------------");
        head = new Node(1);
        System.out.println("head = " + head);
        isPalindrome = isPalindrome(head);
        System.out.println("isPalindrome = " + isPalindrome);
        System.out.println("-------------------------------");
        head = new Node(1);
        head.setNext(new Node(2)).setNext(new Node(2));
        System.out.println("head = " + head);
        isPalindrome = isPalindrome(head);
        System.out.println("isPalindrome = " + isPalindrome);

    }

    public static boolean isPalindrome(Node node) {
        Objects.requireNonNull(node);
        Node reverse = null;
        Node current = node;
        while (current != null) {
            Node newNode = new Node(current.getValue());
            if (reverse == null) {
                reverse = newNode;
            } else {
                newNode.setNext(reverse);
                reverse = newNode;
            }
            current = current.getNext();
        }
        current = node;
        while (current != null && reverse != null) {
            if (current.getValue() != reverse.getValue()) {
                return false;
            }
            current = current.getNext();
            reverse = reverse.getNext();
        }
        return true;
    }
}
