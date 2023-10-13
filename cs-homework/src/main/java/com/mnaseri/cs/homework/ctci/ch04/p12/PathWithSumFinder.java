package com.mnaseri.cs.homework.ctci.ch04.p12;

public class PathWithSumFinder {

    public static void main(String[] args) {
        PathWithSumFinder pathWithSumFinder = new PathWithSumFinder();
        Node ten = new Node(10);
        Node five = new Node(5);
        Node minusFive = new Node(-5);
        Node minusTen = new Node(-10);
        Node four = new Node(4);
        Node six = new Node(6);
        ten.setLeft(five);
        ten.setRight(minusFive);
        five.setRight(minusTen);
        minusFive.setRight(four);
        four.setRight(six);
        int count = pathWithSumFinder.find(ten, 5);
        System.out.println("count = " + count);
    }

    public int find(Node root, int sum) {
        if (root == null) {
            return 0;
        }
        return find(root, sum, 0);
    }

    public int find(Node node, int sum, int total) {
        if (node == null) {
            return 0;
        }
        int current = node.getValue();
        int currentTotal = total + current;
        int paths = find(node.getLeft(), sum, currentTotal) + find(node.getRight(), sum, currentTotal) +
                find(node.getLeft(), sum, 0) + find(node.getRight(), sum, 0);
        if (currentTotal == sum && total != 0) {
            System.out.println("current = " + current);
            return 1 + paths;
        } else {
            return paths;
        }
    }
}
