package com.mnaseri.cs.homework;

import java.util.Stack;

public class Example {
    public static void main(String[] args) {
        System.out.println("isDuplicate");

        Node node = Node.linkedList(new int[]{5, 2, 14, 5, 14, 5, 8, 2, 5, 8, 2});
        System.out.println("five = " + node);
        System.out.println("removeDups(five) = " + singlyLinkedListRemoveDups(node));
        System.out.println("find(1->2->4->5->8, k=3) = " + find(Node.linkedList(new int[]{1, 2, 4, 5, 8}), 3));
        System.out.println("find(1, k=2) = " + find(new Node(1), 2));
        System.out.println("find(5->2->3->4, k=4) = " + find(Node.linkedList(new int[]{5, 2, 3, 4}), 4));
        Node middleManFirstScenario = Node.linkedList(new int[]{5, 2, 3, 4, 8, 7});
        Node middleManSecondScenario = Node.linkedList(new int[]{5, 2, 3, 4, 8});
        System.out.println("middleManFirstScenario = " + middleManFirstScenario);
        deleteMiddleMan(middleManFirstScenario.next.next.next);
        System.out.println("deleteMiddleMan(5, 2, 3, 4, 8, 7) = " + middleManFirstScenario);
        deleteMiddleMan(middleManSecondScenario.next);
        System.out.println("deleteMiddleMan(5, 2, 3, 4, 8) = " + middleManSecondScenario);
        Node partitionData = Node.linkedList(new int[]{3, 5, 8, 5, 10, 2, 1});
        System.out.println(" partitionData: " + partitionData);
        System.out.println(" partitionned Data: " + partition(partitionData, 5));
        Node sumListFirst = Node.linkedList(new int[]{7, 1, 8});
        Node sumListSecond = Node.linkedList(new int[]{5, 9, 5});
        Node sumListThird = Node.linkedList(new int[]{7});
        System.out.println("sumListFirst = " + sumListFirst);
        System.out.println("sumListSecond = " + sumListSecond);
        System.out.println("sumList(sumListFirst, sumListSecond) = " + sumList(sumListFirst, sumListSecond));
        System.out.println("sumListThird = " + sumListThird);
        System.out.println("sumList(sumListFirst, sumListSecond) = " + sumList(sumListFirst, sumListThird));
        System.out.println("sumListForward(sumListFirst, sumListSecond) = " + sumListForward(sumListFirst, sumListSecond));
        Node abba = Node.linkedList(new String[]{"A", "B", "B", "A"});
        Node aba = Node.linkedList(new String[]{"A", "B", "A"});
        Node abcba = Node.linkedList(new String[]{"A", "B", "C", "B", "A"});
        Node nonPalindrome = Node.linkedList(new String[]{"A", "B", "C", "B"});
        System.out.println("isPalindrom(abba) = " + isPalindrome(abba, 4));
        System.out.println("isPalindrom(aba) = " + isPalindrome(aba, 3));
        System.out.println("isPalindrom(abcba) = " + isPalindrome(abcba, 5));
        System.out.println("isPalindrom(nonPalindrome) = " + isPalindrome(nonPalindrome, 4));
        Node first = Node.linkedList(new String[]{"A", "B", "c"});
        Node second = Node.linkedList(new String[]{"A", "B", "B", "A"});

        first = first.appendToTail(second);
        System.out.println("first = " + first);
        System.out.println("second = " + second);
        System.out.println("intersected = " + intersect(first, second));
        System.out.println("intersected(first, first) = " + intersect(first, first));
        Node abcdec = Node.linkedList(new String[]{"A", "B", "C", "D", "E"});
        System.out.println("abcdec = " + abcdec);
        abcdec.gettail().next = abcdec.next.next;
        Node loopNode = findLoopNode(abcdec);
        System.out.println("loopNode = " + loopNode.value);

    }


    public static Node<Integer> singlyLinkedListRemoveDups(Node<Integer> node) {
        if (node == null) {
            throw new IllegalArgumentException("node cannot be null.");
        }
        Node<Integer> visited = node;
        while (visited != null) {
            int value = visited.value;
            Node<Integer> prev = visited;
            Node<Integer> current = prev.next;
            while (current != null) {
                if (current.value == value) {
                    prev.next = current.next;
                    current = current.next;
                } else {
                    prev = prev.next;
                    current = current.next;
                }
            }
            visited = visited.next;
        }
        return node;
    }

    /**
     * find(1->2->4->5->8, k=3)
     * find(1, k=2)
     * find(5->2->3->4, k=4)
     */
    public static Node find(Node node, int k) {
        if (node == null) {
            return null;
        }
        Node runner = node;
        int count = 1;
        while (count < k) {
            if (runner != null) {
                count++;
                runner = runner.next;
            } else {
                return null;
            }
        }
        if (runner == null) {
            return null;
        }
        Node slow = node;
        while (runner.next != null) {
            runner = runner.next;
            slow = slow.next;
        }
        return slow;

    }

    public static boolean deleteMiddleMan(Node node) {
        if (node == null || node.next == null) {
            return false;
        }
        node.value = node.next.value;
        node.next = node.next.next;
        return true;
    }

    public static Node<Integer> partition(Node<Integer> node, int pivot) {
        if (node == null) {
            throw new IllegalArgumentException("node cannot be null.");
        }

        Node<Integer> current = node;
        Node<Integer> s = null;
        Node<Integer> l = null;
        while (current != null) {
            Node<Integer> next = current.next;

            if (current.value < pivot) {
                if (s == null) {
                    s = current;
                    current.next = null;
                } else {
                    current.next = s;
                    s = current;
                }
            } else {
                if (l == null) {
                    l = current;
                    current.next = null;
                } else {
                    current.next = l;
                    l = current;
                }
            }
            current = next;
        }


        if (s != null) {
            current = s;
            while (current.next != null) {
                current = current.next;
            }
            current.next = l;
            return s;
        }
        return node;
    }

    public static Node sumList(Node first, Node second) {
        if (first == null) {
            throw new IllegalArgumentException("first cannot be null.");
        }

        if (second == null) {
            throw new IllegalArgumentException("second cannot be null.");
        }
        return sumList(first, second, 0);

    }

    private static Node<Integer> sumList(Node<Integer> first, Node<Integer> second, int carryOver) {
        if (first == null && second == null) {
            if (carryOver > 0) {
                return new Node(carryOver);
            } else {
                return null;
            }
        }
        int a = first != null ? first.value : 0;
        int b = second != null ? second.value : 0;
        int sum = (a + b + carryOver);
        Node result = new Node(sum % 10);
        int nextCarryOver = sum / 10;
        Node next = sumList(first != null ? first.next : null, second != null ? second.next : null, nextCarryOver);
        if (next != null) {
            result.next = next;
        }
        return result;
    }

    private static Node<Integer> sumListForward(Node<Integer> first, Node<Integer> second) {
        NodePair nodePair = doSumListForward(first, second);
        if (nodePair != null && nodePair.carryOver > 0) {
            Node node = new Node(nodePair.carryOver);
            node.next = nodePair.node;
            return node;
        }
        return null;
    }

    private static NodePair doSumListForward(Node<Integer> first, Node<Integer> second) {
        if (first == null && second == null) {
            return null;
        }
        int a = first != null ? first.value : 0;
        int b = second != null ? second.value : 0;
        NodePair next = doSumListForward(first != null ? first.next : null, second != null ? second.next : null);
        int sum = (a + b + (next != null ? next.carryOver : 0));
        Node result = new Node(sum % 10);
        result.next = next != null ? next.node : null;

        return new NodePair(result, sum / 10);
    }

    public static boolean isPalindrome(Node<String> input, int size) {
        if (input == null) {
            throw new IllegalArgumentException("input cannot be null.");
        }
        Stack<String> stack = new Stack<String>();

        Node<String> node = input;
        int mid = size / 2;
        while (mid > 0) {
            stack.push(node.value);
            node = node.next;
            mid--;
        }
        if (size % 2 != 0) {
            node = node.next;
        }


        return isPalindrome(node, stack);
    }

    private static boolean isPalindrome(Node node, Stack<String> stack) {
        if (node == null && stack.isEmpty()) {
            return true;
        }
        boolean isPalindrome = node != null && node.value.equals(stack.pop());
        return isPalindrome && isPalindrome(node.next, stack);
    }

    public static <E> E intersect(Node<E> first, Node<E> second) {
        //Null check
        int firstSize = 1;
        int secondSize = 1;
        Node current = first;
        Node firstTail = null, secondTail = null;
        while (current.next != null) {
            firstSize++;
            current = current.next;
        }
        firstTail = current;
        current = second;
        while (current.next != null) {
            secondSize++;
            current = current.next;
        }
        secondTail = current;
        if (firstTail != secondTail) {
            return null;
        }
        Node s = null;
        Node l = null;
        if (firstSize >= secondSize) {
            s = second;
            l = first;
        } else {
            s = first;
            l = second;
        }

        int delta = Math.abs(firstSize - secondSize);

        while (delta > 0) {
            l = l.next;
            delta--;
        }

        while (l.next != null && s.next != null) {
            if (l == s) {
                return (E) l.value;
            }
            l = l.next;
            s = s.next;
        }
        return null;

    }

    public static Node<String> findLoopNode(Node<String> node) {
        if (node == null) {
            throw new IllegalArgumentException("a cannot be null");
        }
        Node<String> fast = node;
        Node<String> slow = node;
        //assuming the loop already exists.

        while (true) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                break;
            }
        }
        Node<String> pioneer = fast; //for clarity reasons, fast variable could do the work as well.
        slow = node;
        while (pioneer != slow) {
            pioneer = pioneer.next;
            slow = slow.next;
        }
        return slow;
    }

    private static class Node<E> {
        private Node<E> next;
        private E value;

        public Node(E value) {
            this.value = value;
        }

        public static Node linkedList(int[] items) {
            Node root = new Node(items[0]);
            Node current = root;
            for (int i = 1; i < items.length; i++) {
                int next = items[i];
                current.next = new Node(next);
                current = current.next;
            }

            return root;
        }

        public static Node<String> linkedList(String[] items) {
            Node<String> root = new Node<String>(items[0]);
            Node current = root;
            for (int i = 1; i < items.length; i++) {
                String next = items[i];
                current.next = new Node<String>(next);
                current = current.next;
            }

            return root;
        }

        public Node appendToTail(Node node) {
            Node current = this;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
            return current;
        }

        public Node gettail() {
            Node current = this;
            while (current.next != null) {
                current = current.next;
            }
            return current;
        }


        @Override
        public String toString() {
            return value + (next == null ? "" : "->" + next);
        }
    }

    private static class NodePair {
        private Node node;
        private int carryOver;

        public NodePair(Node node, int carryOver) {
            this.node = node;
            this.carryOver = carryOver;
        }
    }
}

