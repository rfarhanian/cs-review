package com.farhanian.cs.linkedlist;

public class KNodeLinkedListReverser {

    public static LinkedListNode reverseKGroups(LinkedListNode head, int k) {

        int count = 0;
        LinkedListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        current = head;
        int size = count;
        LinkedListNode output = null;
        LinkedListNode lastVisitedReversed = null;
        while (count >= k) {
            LinkedListNode newHead = reverse(current, k);
            if (lastVisitedReversed != null) {
                lastVisitedReversed.next = newHead;
            }
            if (size == count) {
                output = newHead;
            }
            count = count - k;
            System.out.println("count: " + count);
            lastVisitedReversed = current;
            System.out.println("processed current :" + current.data);
            current = current.next;
            System.out.println("next current :" + current);
        }
        return output;
    }

    private static LinkedListNode reverse(LinkedListNode head, int k) {
        LinkedListNode prev = null;
        LinkedListNode current = head;
        for (int i = 0; i < k; i++) {
            System.out.println("i is " + i);
            LinkedListNode next = current.next;
            System.out.println("next is " + next);
            LinkedListNode newPrev = current;
            System.out.println("newPrev " + newPrev.data);
            newPrev.next = prev;
            prev = newPrev;
            current = next;
            System.out.println("current is next now: " + current);
        }
        head.next = current;
        return prev;

    }
}
