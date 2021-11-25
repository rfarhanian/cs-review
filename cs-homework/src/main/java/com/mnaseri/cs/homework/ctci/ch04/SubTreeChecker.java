package com.mnaseri.cs.homework.ctci.ch04;

public class SubTreeChecker {

    public static void main(String[] args) {
        Node root = new Node(50);
        Node twenty = new Node(20);
        Node twentyFive = new Node(25);
        Node sixty = new Node(60);
        Node sixtyFive = new Node(65);
        Node ten = new Node(10);
        Node five = new Node(5);
        Node fifteen = new Node(15);
        Node seventy = new Node(70);
        Node eighty = new Node(80);
        root.left = twenty;
        twenty.left = ten;
        twenty.right = twentyFive;
        ten.left = five;
        ten.right = fifteen;
        root.right = sixty;
        sixty.right = seventy;
        seventy.left = sixtyFive;
        seventy.right = eighty;
        System.out.println("SubTreeChecker.check(root, new Node(50)) = " + SubTreeChecker.check(root, new Node(50)));
        System.out.println("SubTreeChecker.check(root, new Node(80)) = " + SubTreeChecker.check(root, new Node(80)));
        System.out.println("SubTreeChecker.check(root, new Node(70)) = " + SubTreeChecker.check(root, seventy));

    }

    public static boolean check(Node t1, Node t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.value.equals(t2.value)) {
            return matchSubTree(t1.left, t2.left) && matchSubTree(t1.right, t2.right);
        } else {
            return check(t1.left, t2) || check(t1.right, t2);
        }
    }

    private static boolean matchSubTree(Node t1, Node t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null || t2 == null) {
            return false;
        } else if (!t1.value.equals(t2.value)) {
            return false;
        } else {
            return matchSubTree(t1.left, t2.left) && matchSubTree(t1.right, t2.right);
        }

    }

    private static class Node {
        private Node left, right;
        private Integer value;

        public Node(Integer value) {
            this.value = value;
        }
    }
}