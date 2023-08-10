package com.mnaseri.cs.homework.ctci.ch10;

import java.util.Arrays;


public class RankFromStream {

    private Node root = null;

    public static void main(String[] args) {
        int[] items = new int[]{5, 1, 4, 4, 5, 9, 7, 13, 3};
        RankFromStream rankFromStream = new RankFromStream();
        for (int item : items) {
            rankFromStream.track(item);
        }

        for (int item : items) {
            int rank = rankFromStream.getRankFromNumber(item);
            System.out.println("item:" + item + "==>rank:" + rank);
        }
        Arrays.sort(items);
        System.out.println(Arrays.toString(items));

    }

    public void track(int x) {
        if (root == null) {
            root = new Node(x);
        } else {
            insert(root, x);
        }
    }

    public int getRankFromNumber(int x) {
        return getRankFromNumber(root, x);
    }

    private int getRankFromNumber(Node root, int x) {
        if (root == null) {
            return -1;
        }

        if (x == root.value) {
            return root.leftSize;
        }
        if (x < root.value) {
            return getRankFromNumber(root.left, x);
        } else {
            int right = getRankFromNumber(root.right, x);
            if (right == -1) {
                return -1;
            }
            return 1 + root.leftSize + right;
        }
    }

    private void insert(Node root, int value) {

        if (value <= root.value) {
            if (root.left == null) {
                root.left = new Node(value);
            } else {
                insert(root.left, value);
            }
            root.leftSize += 1;
        } else {
            if (root.right == null) {
                root.right = new Node(value);
            } else {
                insert(root.right, value);
            }
        }

    }

    private Node find(int x) {
        return find(root, x);
    }

    private Node find(Node root, int x) {
        if (root == null) {
            return null;
        }

        if (x == root.value) {
            return root;
        }
        if (x <= root.value) {
            return find(root.left, x);
        } else {
            return find(root.right, x);
        }
    }


    private static class Node {

        private Node left, right;
        private int value, leftSize = 0;

        public Node(int value) {
            this.value = value;
        }
        //constructor with value and rank.
        //constructor with value

    }

}