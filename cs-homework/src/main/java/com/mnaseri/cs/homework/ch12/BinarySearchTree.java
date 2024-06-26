package com.mnaseri.cs.homework.ch12;

import java.util.Arrays;
import java.util.List;

/**
 * @see <a href="https://algs4.cs.princeton.edu/32bst/BST.java.html">Sedgewick BST</a>
 */
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
            System.out.println("bst.size(" + item + ") = " + bst.find(item).getLeftSize());
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
        System.out.println("bst.ceiling(81) = " + bst.ceiling(81));
        System.out.println("bst.ceiling(92) = " + bst.ceiling(92));
        System.out.println("bst.ceiling(101) = " + bst.ceiling(101));
        System.out.println("bst.floor(92) = " + bst.floor(92));
        System.out.println("bst.floor(122) = " + bst.floor(122));
        System.out.println("bst.floor(80) = " + bst.floor(80));
        System.out.println("bst.floor(79) = " + bst.floor(79));
        System.out.println("bst.floor(201) = " + bst.floor(201));
    }

    /**
     * (while you are implementing find method) GO TO THE RIGHT, IF FOUND NOTHING, RETURN ROOT
     * <p>
     * Returns the largest key in the symbol table smaller than or equal to value.
     * Remember that you are looking for the largest, so as part of your In order Walk,
     * you check larger scenario first (root.value >value) and then you will reach a node
     * that may have the answer on the left side(because you are looking for a key smaller or equal).
     * If there is an answer on the left side, it will be returned otherwise root is the answer.
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
        if (value == root.getValue()) {
            return root;
        }
        if (value < root.getValue()) {
            return floor(root.getLeft(), value);
        } else {
            Node right = floor(root.getRight(), value);
            return (right != null) ? right : root;
        }
    }

    /**
     * (while implementing search method), GO TO THE LEFT, IF FOUND NOTHING, RETURN THE ROOT
     *
     * Returns the smallest key in the symbol table greater than or equal to value
     * Remember that you are looking for the smallest key, so as part of your In order Walk,
     * you check smaller scenario first (root.value < value) and then you will reach a node
     * that may have the answer on the right side(because you are looking for a key greater or equal).
     * If there is an answer on the right side, it will be returned otherwise root is the answer.
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
        if (value > root.getValue()) {
            return ceiling(root.getRight(), value);
        } else {
            Node left = ceiling(root.getLeft(), value);
            return left != null ? left : root;
        }
    }

    public boolean isBst(Node node) {
        return isBst(node, null, null);
    }

    private boolean isBst(Node root, Integer min, Integer max) {
        boolean smallerThanMax = (max != null) ? (root.getValue() < max) : true;
        boolean largerThanMin = (min != null) ? (root.getValue() > min) : true;
        return smallerThanMax && largerThanMin &&
                isBst(root.getLeft(), min, root.getValue()) &&
                isBst(root.getRight(), root.getValue(), max);
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
        if (value < root.getValue()) {
            root.incrementLeftSize();
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
            while (parent != null && current.equals(parent.getRight())) {
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
            while (parent != null && current.equals(parent.getLeft())) {
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
        return root.getLeft() != null ? min(root.getLeft()) : root;
    }

    /**
     * Return the node of a given {@code rank}.
     * This node has the property that there are {@code rank} nodes in
     * that are smaller. In other words, this node is the
     * ({@code rank})st smallest key in the BST.
     *
     * @param rank the order statistic
     * @return the value with given {@code rank}
     * @throws IllegalArgumentException unless {@code rank} is between 0 and <em>n</em>–1
     * @see <a href="https://algs4.cs.princeton.edu/32bst/BST.java.html">Check Sedgewick implementation for rank > size() check</a>
     */
    public Integer select(int rank) {
        if (rank < 0 /*|| rank > size()*/) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }

    private Integer select(Node node, int rank) {
        if (node == null) {
            return 0;
        }
        int current = (node.getLeft() != null ? node.getLeft().getLeftSize() : 0) + 1;
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
