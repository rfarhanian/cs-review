package com.farhanian.cs.linkedlist;

/**
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/solution-reverse-linked-list-ii">The problem</a>
 */
public class ReverseLinkedListII {
    public static LinkedListNode reverseBetween(LinkedListNode head, int left, int right) {

        if (left == right) {
            return head;
        }
        LinkedListNode sentinel = new LinkedListNode(-1);
        LinkedListNode leftRunner = null, rightRunner = null;
        sentinel.next = head;
        for (int i = 0; i < left; i++) {
            leftRunner = leftRunner == null ? sentinel : leftRunner.next;
            rightRunner = rightRunner == null ? sentinel : rightRunner.next;
        }
        for (int i = 0; i < (right - left); i++) {
            rightRunner = rightRunner.next;
        }
        LinkedListNode reversed = doReverse(leftRunner.next, rightRunner.next);
        leftRunner.next = reversed;
        LinkedListNode output = leftRunner == sentinel ? reversed : head;
        return output;
    }

    private static LinkedListNode doReverse(LinkedListNode from, LinkedListNode to) {
        LinkedListNode finalTail = to.next;
        LinkedListNode prev = null;
        LinkedListNode newHead = null;
        LinkedListNode current = from;
        while (current != null && current != to) {
            LinkedListNode next = current.next;
            LinkedListNode newPrev = current;
            newPrev.next = prev;
            if (prev == null) {
                newHead = newPrev;
            }
            prev = newPrev;
            current = next;
        }
        LinkedListNode newPrev = to;
        newPrev.next = prev;
        prev = newPrev;
        newHead.next = finalTail;
        //elementBeforeFrom.next = prev
        return prev;
    }
}