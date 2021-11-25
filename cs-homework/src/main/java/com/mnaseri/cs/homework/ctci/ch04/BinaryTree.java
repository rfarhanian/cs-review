package com.mnaseri.cs.homework.ctci.ch04;

import java.util.Random;

/**
 * Problem 4.11 : RandomNode
 */
public class BinaryTree {

    private Node root;
    private int size = -1;
    private Random rand = new Random();

    public void insert(int value) {
        if (root == null) {
            root = new Node(value, ++size);
        } else {
            insert(root, value);
        }
    }

    private void insert(Node node, int value) {
        if (value < node.value) {
            if (node.left != null) {
                insert(node.left, value);
            } else {
                node.left = new Node(value, ++size);
            }
        } else {
            if (node.right != null) {
                insert(node.right, value);
            } else {
                node.right = new Node(value, ++size);
            }
        }
    }

    public Node getRandomNode() {
        if (root == null) {
            return null;
        }
        int random = rand.nextInt(size);
        return find(root, random);
    }

    private Node find(Node node, int index) {
        if (node == null) {
            return null;
        }

        if (node.no == index) {
            return node;
        } else {
            Node left = find(node.left, index);
            if (left != null) {
                return left;
            }
            Node right = find(node.right, index);
            if (right != null) {
                return right;
            }
            return null;
        }
    }


    public static class Node {
        private Node left, right;
        private int value, no;

        public Node(int value, int no) {
            this.value = value;
            this.no = no;
        }
    }
}