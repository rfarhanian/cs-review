package com.mnaseri.cs.homework.problem.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/reverse-linked-list/">The problem</a>
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode current) {
        if (current == null) {
            return null;
        }
        ListNode output = null;
        while (current != null) {
            ListNode newNode = new ListNode(current.val);
            if (output == null) {
                output = newNode;
            } else {
                newNode.next = output;
                output = newNode;
            }
            current = current.next;
        }
        return output;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
