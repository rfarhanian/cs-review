package com.mmnaseri.cs.ctci.ch02.p01;

import com.mmnaseri.cs.ctci.ch02.DefaultSinglyLinkedNode;
import com.mmnaseri.cs.ctci.ch02.SinglyLinkedNode;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 2:18 PM)
 */
public class LookAheadDuplicateRemover<E, N extends SinglyLinkedNode<E, N>> implements DuplicateRemover<E, N> {

    public static void main(String[] args) {
        int[] input = new int[]{5, 2, 14, 5, 14, 5};
        SinglyLinkedNode<Integer, DefaultSinglyLinkedNode<Integer>> node = new DefaultSinglyLinkedNode<>();
        node.setData(input[0]);
        SinglyLinkedNode<Integer, DefaultSinglyLinkedNode<Integer>> current = node;

        for (int item : input) {
            DefaultSinglyLinkedNode<Integer> next = new DefaultSinglyLinkedNode<>();
            next.setData(item);
            current.setNext(next);
            current = current.next();
        }

        System.out.println("node = " + node);
        LookAheadDuplicateRemover remover = new LookAheadDuplicateRemover();
        remover.removeDuplicates(node);
        System.out.println("node = " + node);
    }

    @Override
    public void removeDuplicates(N head) {
        N seen = head;
        while (seen != null) {
            N current = seen.next();
            N previous = seen;
            while (current != null) {
                if (current.data() == seen.data()) {
                    previous.setNext(current.next());
                } else {
                    previous = current;
                }
                current = current.next();
            }
            seen = seen.next();
        }
    }

}
