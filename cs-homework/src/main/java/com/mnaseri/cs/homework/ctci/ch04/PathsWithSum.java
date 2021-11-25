package com.mnaseri.cs.homework.ctci.ch04;

public class PathsWithSum {

    public static void main(String[] args) {
        Node fifty = new Node(50);
        Node seventy = new Node(70);
        Node sixty = new Node(60);
        Node forty = new Node(40);
        Node eighty = new Node(80);
        Node twenty = new Node(20);
        Node thirty = new Node(30);
        Node minusTen = new Node(-10);
        Node oneOtwenty = new Node(120);
        Node eleven = new Node(11);
        Node eighteen = new Node(18);
        fifty.left = seventy;
        fifty.right = sixty;
        seventy.left = forty;
        seventy.right = eighty;
        sixty.left = twenty;
        sixty.right = minusTen;
        minusTen.left = eleven;
        minusTen.right = oneOtwenty;
        eleven.right = eighteen;
        twenty.left = thirty;
        thirty.right = new Node(110);
        int result = find(fifty, 110);
        System.out.println("result = " + result);

        Node plus1 = new Node(1);
        Node minus2 = new Node(-1);
        Node minus3 = new Node(-3);
        Node anotherOne = new Node(1);
        Node three = new Node(3);
        Node minus2Again = new Node(-2);
        Node minus1 = new Node(-1);
        plus1.left = minus2;
        plus1.right = minus3;
        minus3.left = minus2Again;
        minus2.left = anotherOne;
        minus2.right = three;
        anotherOne.left = minus1;
        result = find(plus1, -1);
        System.out.println("result = " + result);

    }

    public static int find(Node node, int pathSum) {
        if (node == null) {
            return 0;
        }
        return sum(node, pathSum, 0) + find(node.left, pathSum) + find(node.right, pathSum);
    }

    private static int sum(Node node, int pathSum, int currentSum) {
        if (node == null) {
            return 0;
        }
        currentSum += node.value;
        if (currentSum == pathSum) {
            return 1;
        } else {
            return sum(node.left, pathSum, currentSum) + sum(node.right, pathSum, currentSum);
        }
    }

    public static class Node {
        private Node left, right;
        private int value;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }
}