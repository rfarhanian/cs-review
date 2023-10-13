package com.mmnaseri.cs.ctci.ch02.p03;

import com.mmnaseri.cs.ctci.ch02.Node;

public class MiddleDeleterUtil {
    public static void main(String[] args) {

    }

    public static void delete(Node head) {
        head.setValue(head.getNext().getValue());
        head.setNext(head.getNext().getNext());

    }

}
