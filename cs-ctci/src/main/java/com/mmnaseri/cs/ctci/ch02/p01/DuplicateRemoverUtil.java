package com.mmnaseri.cs.ctci.ch02.p01;

import com.mmnaseri.cs.ctci.ch02.Node;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DuplicateRemoverUtil {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.setNext(new Node(2)).setNext(new Node(3)).setNext(new Node(1));
        System.out.println("head = " + head);
        dedupe(head);
        System.out.println("dedupe(input) = " + head);

        head = new Node(1);
        head.setNext(new Node(2)).setNext(new Node(3)).setNext(new Node(1));
        System.out.println("look ahead head = " + head);
        lookAheadDedupe(head);
        System.out.println("lookAheadDedupe(input) = " + head);
    }

    public static void dedupe(Node input) {
        Objects.requireNonNull(input);
        Set<Integer> set = new HashSet<>();
        Node current = input;
        set.add(current.getValue());
        while (current.getNext() != null) {
            if (set.contains(current.getNext().getValue())) {
                current.setNext(current.getNext().getNext());
            } else {
                set.add(input.getNext().getValue());
                current = current.getNext();
            }
        }
    }

    public static void lookAheadDedupe(Node input) {
        Objects.requireNonNull(input);
        Node seen = input;
        while (seen != null) {
            Node current = seen.getNext();
            handleDuplicate(seen.getValue(), seen);
            seen = current;
        }
    }

    private static void handleDuplicate(int visited, Node input) {
        while (input.getNext() != null) {
            if (input.getNext().getValue() == visited) {
                input.setNext(input.getNext().getNext());
            } else {
                input = input.getNext();
            }
        }
    }
}
