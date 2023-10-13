package com.mmnaseri.cs.ctci.ch02.p02;

import com.mmnaseri.cs.ctci.ch02.Node;

import java.util.Objects;

public class KthToLastFinderUtil {


    public static Node find(Node head, int k) {
        Objects.requireNonNull(head);
        Node runner = head;
        Node ahead = head;
        for (int i = 0; i < k; i++) {
            if (ahead != null) {
                ahead = ahead.getNext();
            } else {
                return null;
            }
        }
        while (ahead != null) {
            runner = runner.getNext();
            ahead = ahead.getNext();
        }
        return runner;
    }
}
