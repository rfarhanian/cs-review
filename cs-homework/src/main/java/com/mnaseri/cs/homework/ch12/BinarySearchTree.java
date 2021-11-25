package com.mnaseri.cs.homework.ch12;

import java.util.Arrays;
import java.util.List;

public class BinarySearchTree {
    private Node root;

    public static void main(String[] args) {
        Node oneTwenty = new Node(120);
        Node oneFifty = new Node(150);
        Node oneHundred = new Node(100);
        Node twoHundred = new Node(200);
        Node ninty = new Node(90);
        Node eighty = new Node(80);
        Node nintyFive = new Node(95);
        nintyFive.setRight(oneHundred);
        oneTwenty.setLeft(ninty);
        oneTwenty.setRight(oneFifty);
        ninty.setLeft(eighty);
        ninty.setRight(nintyFive);
        oneFifty.setRight(twoHundred);
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(120);
        bst.insert(90);
        bst.insert(150);
        bst.insert(80);
        bst.insert(95);
        bst.insert(100);
        bst.insert(200);
        System.out.println("bst.toString() = " + bst.toString());
        System.out.println("bst.find(100) = " + bst.find(100));
        System.out.println("bst.find(200) = " + bst.find(200));
        System.out.println("bst.find(120) = " + bst.find(120));
        System.out.println("bst.min() = " + bst.min());
        System.out.println("bst.max() = " + bst.max());
        System.out.println("bst.successor(ninty) = " + bst.successor(bst.find(90)));
        System.out.println("bst.successor(oneHundred) = " + bst.successor(bst.find(100)));
        System.out.println("bst.successor(oneTwenty) = " + bst.successor(bst.find(120)));
        System.out.println("bst.predecessor(ninty) = " + bst.predecessor(bst.find(90)));
        System.out.println("bst.predecessor(oneHundred) = " + bst.predecessor(bst.find(100)));
        System.out.println("bst.predecessor(oneTwenty) = " + bst.predecessor(bst.find(120)));
        List<Integer> items = Arrays.asList(80, 90, 95, 100, 120, 150, 200);

        for (int item : items) {
            System.out.println("bst.size(" + item + ") = " + bst.find(item).getSize());
        }
        for (int item : items) {
            System.out.println("bst.rank(" + item + ") = " + bst.rank(item));
        }
        for (int i = 1; i <= items.size(); i++) {
            System.out.println("bst.select(" + i + ") = " + bst.select(i));
        }
        for (int item : items) {
            System.out.println("bst.height(" + item + ") = " + bst.height(item));
        }
        for (int item : items) {
            System.out.println("bst.isBst(" + item + ") = " + bst.isBst(bst.find(item)));
        }
        System.out.println("bst.ceiling(80) = " + bst.ceiling(81));
        System.out.println("bst.ceiling(92) = " + bst.ceiling(92));
        System.out.println("bst.ceiling(101) = " + bst.ceiling(101));
        System.out.println("bst.floor(92) = " + bst.floor(92));
        System.out.println("bst.floor(122) = " + bst.floor(122));
        System.out.println("bst.floor(80) = " + bst.floor(80));
        System.out.println("bst.floor(79) = " + bst.floor(79));
        System.out.println("bst.floor(201) = " + bst.floor(201));
    }

    /**
     * Returns the largest key in the symbol table smaller than or equal to value
     *
     * @param value
     */
    public Node floor(int value) {
        if (root == null) {
            return null;
        }
        return floor(root, value);
    }

    private Node floor(Node root, int value) {
        if (root == null) {
            return null;
        }
        int current = root.getValue();
        if (current > value) {
            return floor(root.getLeft(), value);
        } else {
            Node right = floor(root.getRight(), value);
            return (right != null) ? right : root;
        }
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to value
     *
     * @param value
     */
    public Node ceiling(int value) {
        return ceiling(root, value);
    }

    private Node ceiling(Node root, int value) {
        if (root == null) {
            return null;
        }
        if (root.getValue() == value) {
            return root;
        }
        if (root.getValue() < value) {
            return ceiling(root.getRight(), value);
        } else {
            Node left = ceiling(root.getLeft(), value);
            return left != null ? left : root;
        }
    }

    public boolean isBst(Node node) {
        return isBst(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
//        return isBst(root, null, null);
    }

    private boolean isBst(Node node, Node min, Node max) {
        if (node == null) {
            return true;
        }
        if (min != null && node.getValue() <= min.getValue()) {
            return false;
        }
        if (max != null && node.getValue() >= max.getValue()) {
            return false;
        }
        return isBst(node.getLeft(), min, node) && isBst(node.getRight(), node, max);
    }

    private boolean isBst(Node node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.getValue() <= min) {
            return false;
        }
        if (node.getValue() >= max) {
            return false;
        }
        return isBst(node.getLeft(), min, node.getValue()) && isBst(node.getRight(), node.getValue(), max);
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
        } else {
            insert(root, value);
        }
    }

    public Node find(int value) {
        return find(root, value);
    }

    private Node find(Node root, int value) {
        if (root == null) {
            return null;
        }

        if (root.getValue() == value) {
            return root;
        } else if (value > root.getValue()) {
            return find(root.getRight(), value);
        } else {
            return find(root.getLeft(), value);
        }
    }

    private void insert(Node root, int value) {
        root.incrementSize();
        if (value < root.getValue()) {
            if (root.getLeft() != null) {
                insert(root.getLeft(), value);
            } else {
                Node node = new Node(value);
                node.setParent(root);
                root.setLeft(node);
            }
        } else {
            if (root.getRight() != null) {
                insert(root.getRight(), value);
            } else {
                Node node = new Node(value);
                node.setParent(root);
                root.setRight(node);
            }
        }
    }

    public Node successor(Node node) {
        if (node == null) {
            return null;
        }
        if (node.getRight() != null) {
            return min(node.getRight());
        } else {
            Node parent = node.getParent();
            Node current = node;
            while (parent != null && current == parent.getRight()) {
                current = parent;
                parent = parent.getParent();
            }
            return parent;
        }
    }

    public Node predecessor(Node node) {
        if (node == null) {
            return null;
        }
        if (node.getLeft() != null) {
            return max(node.getLeft());
        } else {
            Node parent = node.getParent();
            Node current = node;
            while (parent != null && current == parent.getLeft()) {
                current = parent;
                parent = parent.getParent();
            }
            return parent;
        }
    }

    public Node max() {
        if (root == null)
            return null;
        return max(root);
    }

    private Node max(Node root) {
        return root.getRight() == null ? root : max(root.getRight());
    }

    public Node min() {
        if (root == null)
            return null;
        return min(root);
    }

    private Node min(Node root) {
        if (root == null) {
            return null;
        }
        return root.getLeft() != null ? min(root.getLeft()) : root;
    }

    /**
     * Return the node of a given {@code rank}.
     * This node has the property that there are {@code rank} nodes in
     * that are smaller. In other words, this node is the
     * ({@code rank}+1)st smallest key in the BST.
     *
     * @param rank the order statistic
     * @return the value with given {@code rank}
     * @throws IllegalArgumentException unless {@code rank} is between 0 and <em>n</em>–1
     */
    public Integer select(int rank) {
        if (rank < 0 || rank > root.getSize()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }

    private Integer select(Node node, int rank) {
        if (node == null) {
            return 0;
        }
        int current = (node.getLeft() != null ? node.getLeft().getSize() : 0) + 1;
        if (rank == current) {
            return node.getValue();
        }
        if (rank < current) {
            return select(node.getLeft(), rank);
        } else {
            return select(node.getRight(), rank - current);
        }
    }

    /**
     * https://www.youtube.com/watch?v=uwWOUAdOTig
     *
     * @param value
     * @return
     */
    public int rank(int value) {
        return rank(root, value);
    }

    private int rank(Node node, int targetValue) {
        if (node == null) {
            return 0;
        }

        if (node.getValue() == targetValue) {
            return (node.getLeft() == null ? 0 : node.getLeft().getSize()) + 1;
        } else if (node.getValue() > targetValue) {
            return rank(node.getLeft(), targetValue);
        } else {
            return 1 + (node.getLeft() == null ? 0 : node.getLeft().getSize()) + rank(node.getRight(), targetValue);
        }

    }

    public int height(int value) {
        Node node = find(root, value);
        if (node == null) {
            return -1;
        }
        return height(node);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }


}
