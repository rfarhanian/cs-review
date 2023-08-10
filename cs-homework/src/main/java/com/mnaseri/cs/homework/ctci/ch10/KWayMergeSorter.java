package com.mnaseri.cs.homework.ctci.ch10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KWayMergeSorter {

    public static void main(String[] args) {
        ListNode first = createList(Arrays.asList(1000, 1100, 1200, 1300));
        ListNode second = createList(Arrays.asList(100, 200, 300, 400));
        ListNode third = createList(Arrays.asList(10, 20, 330, 430, 1150));
        ListNode fourth = createList(Arrays.asList(248));
        ListNode output = mergeKLists(new ListNode[]{first, second, third, fourth});
        log(output);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Item> pq = new PriorityQueue(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                return Integer.valueOf(a.getNode().val).compareTo(b.getNode().val);
            }
        });
        ListNode output = new ListNode();
        ListNode last = output;
        for (int i = 0; i < lists.length; i++) {
            ListNode current = lists[i];
            pq.add(new Item(i, current));
        }

        while (!pq.isEmpty()) {
            Item item = pq.remove();
            int listNo = item.i;
            last.next = new ListNode(item.getNode().val);
            last = last.next;
            ListNode current = item.node.next;
            if (current != null) {
                pq.add(new Item(listNo, current));
            }
        }
        return output.next;
    }

    private static void log(ListNode output) {
        ListNode current = output;
        while (current != null) {
            System.out.println(current.val);
            current = current.next;
        }
    }

    private static ListNode createList(List<Integer> input) {
        ListNode head = new ListNode(input.get(0));
        ListNode current = head;
        for (int i = 1, inputSize = input.size(); i < inputSize; i++) {
            Integer item = input.get(i);
            ListNode theItem = new ListNode(item);
            current.next = theItem;
            current = current.next;
        }
        return head;
    }


    public static class Item {
        private int i;
        private ListNode node;

        public Item(int i, ListNode node) {
            this.i = i;
            this.node = node;
        }

        public int getI() {
            return this.i;
        }

        public ListNode getNode() {
            return this.node;
        }

    }

    public static class ListNode {
        private int val;
        private ListNode next;

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
