package com.farhanian.cs.linkedlist;

/**
 * @see <a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list/">The problem</a>
 */
public class LinkedListNthNodeRemover {
    public LinkedListNode removeNthFromEnd(LinkedListNode head, int n) {
        if (n == 1 && head.next == null) {
            return head.next;
        }
        LinkedListNode slowRunner = head;
        LinkedListNode fastRunner = head;
        int cursor = 0;
        while (cursor < n) {
            fastRunner = fastRunner.next;
            cursor++;
        }
        if (fastRunner == null) {
            return slowRunner.next;
        }

        while (fastRunner.next != null) {
            fastRunner = fastRunner.next;
            slowRunner = slowRunner.next;
        }
        slowRunner.next = (slowRunner.next != null) ? slowRunner.next.next : null;
        return head;
    }
}
