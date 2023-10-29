package com.mnaseri.cs.homework.ctci.ch10.p10;

public class RankFromStream {

    private Node root;

    public static void main(String[] args) {
        RankFromStream tree = new RankFromStream();
        tree.track(5);
        tree.track(1);
        tree.track(4);
        tree.track(4);
        tree.track(5);
        tree.track(9);
        tree.track(7);
        tree.track(13);
        tree.track(3);
        System.out.println("tree.getRankOfNumber(0) = " + tree.getRankOfNumber(0));
        System.out.println("tree.getRankOfNumber(1) = " + tree.getRankOfNumber(1));
        System.out.println("tree.getRankOfNumber(3) = " + tree.getRankOfNumber(3));
        System.out.println("tree.getRankOfNumber(4) = " + tree.getRankOfNumber(4));
        System.out.println("tree.getRankOfNumber(8) = " + tree.getRankOfNumber(8));

    }

    public void track(int x) {
        if (root == null) {
            root = new Node(x);
        } else {
            track(root, x);
        }

    }

    public void track(Node node, int x) {
        if (x <= node.getValue()) {
            node.incrementLeftSize();
            if (node.getLeft() != null) {
                track(node.getLeft(), x);
            } else {
                node.setLeft(new Node(x));
            }
        } else {
            if (node.getRight() != null) {
                track(node.getRight(), x);
            } else {
                node.setRight(new Node(x));
            }
        }

    }

    public int getRankOfNumber(int x) {
        if (root == null) {
            return -1;
        }
        return getRankOfNumber(root, x, 0);
    }

    private int getRankOfNumber(Node node, int x, int partialSolution) {
        //node =2,x=5, partialSolution=0
        //node =4,x=5, partialSolution=2
        //node =5,x=5, partialSolution=4 -> 4
        if (node == null) {
            return -1;
        }
        if (x == node.getValue()) {
            return partialSolution + node.getLeftSize();
        }
        if (x > node.getValue()) {
            return getRankOfNumber(node.getRight(), x, partialSolution + node.getLeftSize() + 1);
        } else {
            return getRankOfNumber(node.getLeft(), x, partialSolution);
        }
    }

    private int rank(Node node, int targetValue) {
        if (node == null) {
            return -1;
        }

        if (targetValue == node.getValue()) {
            return node.getLeftSize() + 1;
        } else if (targetValue < node.getValue()) {
            return rank(node.getLeft(), targetValue);
        } else {
            int rightRank = rank(node.getRight(), targetValue);
            if (rightRank == -1) {
                return -1;
            } else {
                return node.getLeftSize() + 1 + rightRank;
            }
        }
    }

    public static class Node {
        private Node left, right;
        private int value;
        private int leftSize = 0;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int incrementLeftSize() {
            return leftSize++;
        }

        public int getLeftSize() {
            return leftSize;
        }

        @Override
        public String toString() {
            return "(" + value + ")";
        }
    }
}
