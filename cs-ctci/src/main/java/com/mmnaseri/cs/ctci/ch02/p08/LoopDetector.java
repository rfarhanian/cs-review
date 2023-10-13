package com.mmnaseri.cs.ctci.ch02.p08;

import com.mmnaseri.cs.ctci.ch02.Node;

/**
 * This problem is solved assuming that the input is already a linked list with a loop.
 * https://starrycode.github.io/detecting_a_loop_in_linked_lists_and_finding_the_start_of_the_loop
 */
public class LoopDetector {

    public static void main(String[] args) {
        Node first = new Node(1);
        first.setNext(new Node(2)).setNext(new Node(3)).setNext(first);
        System.out.println("first = " + first.getValue());
        Node node = detect(first);
        System.out.println("loop Node = " + node.getValue());
        System.out.println("---------------------------");
        first = new Node(1);
        Node two = new Node(2);
        first.setNext(two).setNext(new Node(3)).setNext(two);
        System.out.println("first = " + first.getValue());
        node = detect(first);
        System.out.println("loop Node = " + node.getValue());
        System.out.println("---------------------------");
        first = new Node(1);
        first.setNext(first);
        System.out.println("first = " + first.getValue());
        node = detect(first);
        System.out.println("loop Node = " + node.getValue());
        System.out.println("---------------------------");
        first = new Node(1);
        Node four = new Node(4);
        first.setNext(new Node(2)).setNext(new Node(3)).setNext(four).setNext(new Node(5)).
                setNext(new Node(6)).setNext(new Node(7)).setNext(new Node(8)).setNext(new Node(9))
                .setNext(new Node(10)).setNext(new Node(11)).setNext(four);
        System.out.println("first = " + first.getValue());
        node = detect(first);
        System.out.println("loop Node = " + node.getValue());
        System.out.println("---------------------------");
    }

    public static Node detect(Node loopedNode) {
        if (loopedNode == null) {
            return null;
        }
        Node fast = loopedNode;
        Node slow = loopedNode;
        while (true) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if (fast == slow) {
                break;
            }
        }
        Node result = loopedNode;
        while (result != fast) {
            result = result.getNext();
            fast = fast.getNext();
        }
        return result;
    }
}
