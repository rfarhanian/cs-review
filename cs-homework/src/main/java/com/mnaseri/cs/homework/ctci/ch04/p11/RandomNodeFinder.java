package com.mnaseri.cs.homework.ctci.ch04.p11;

import java.util.Random;

public class RandomNodeFinder {

    private static final Random RANDOM = new Random();
    private Node root;

    public static void main(String[] args) {

    }

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
        }
        insert(root, value);
    }

    private void insert(Node node, int value) {
        node.incrementSize();
        if (value < node.getValue()) {
            Node left = node.getLeft();
            if (left != null) {
                insert(left, value);
            } else {
                node.setLeft(new Node(value));
            }
        } else {
            Node right = node.getRight();
            if (right != null) {
                insert(right, value);
            } else {
                node.setRight(new Node(value));
            }
        }
    }

    public Node find(int value) {
        if (root == null) {
            return null;
        }
        return find(root, value);
    }

    private Node find(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (value == node.getValue()) {
            return node;
        }
        if (value < node.getValue()) {
            return find(node.getLeft(), value);
        } else {
            return find(node.getRight(), value);
        }
    }

    public boolean delete(int value) {
        return false;
    }

    public Node getRandomNode() {
        if (root == null) {
            return null;
        }
        int rank = RANDOM.nextInt(root.getSize() + 1);
        return select(root, rank);
    }

    public Node select(Node node, int rank) {
        if (node == null) {
            return null;
        }
        int current = (node.getLeft() != null ? node.getLeft().getSize() : 0) + 1;
        if (rank == current) {
            return node;
        }
        if (rank < current) {
            return select(node.getLeft(), rank);
        } else {
            return select(node.getRight(), rank - current);
        }
    }


}
