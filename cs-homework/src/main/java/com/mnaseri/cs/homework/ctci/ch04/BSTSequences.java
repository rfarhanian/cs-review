package com.mnaseri.cs.homework.ctci.ch04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * A binary search tree was created by traversing through an array from left to right and inserting each element.
 * Given a binary search tree with distinct elements, print all possible arrays that could have led to this tree.
 * <p>
 * <p>
 * If you look at this problem with a comprehensive example, it will confuse you.
 * Look at it with 5 as a root, 2 as the left child and 10 as the right child.
 * Consider that both children are a list with one item.
 * <p>
 * Read for thorough explanation: https://medium.com/@jackwootton/binary-search-tree-sequences-53163b1f374a
 */
public class BSTSequences {

    public static void main(String[] args) {

        BSTSequences bstSequences = new BSTSequences();
        // weave test
        ArrayList<List<Integer>> results = new ArrayList<>();
        bstSequences.weave(new LinkedList<>(Arrays.asList(1, 2)), new LinkedList<>(Arrays.asList(3, 4)), results, new LinkedList<>());
//        System.out.println("results = " + results);

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
        List<List<Integer>> sequences = bstSequences.sequences(root);
        System.out.println("sequences = " + sequences);
    }

    public List<List<Integer>> sequences(Node root) {

        List<List<Integer>> solutions = new ArrayList<>();
        if (root == null) {
            solutions.add(new LinkedList<>());
            return solutions;
        }
        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(root.value);
        List<List<Integer>> leftSequences = sequences(root.left);
        List<List<Integer>> rightSequences = sequences(root.right);
        for (List<Integer> leftSeq : leftSequences) {
            for (List<Integer> rightSeq : rightSequences) {
                List<List<Integer>> weaved = new ArrayList<>();
                weave(leftSeq, rightSeq, weaved, prefix);
                solutions.addAll(weaved);
            }
        }
        return solutions;
    }

    public void weave(List<Integer> first, List<Integer> second, List<List<Integer>> solutions, List<Integer> solution) {
        if (first.isEmpty() || second.isEmpty()) {
            LinkedList<Integer> result = new LinkedList<>(solution);
            result.addAll(first);
            result.addAll(second);
            solutions.add(result);
            return;
        }

        LinkedList<Integer> newSolution = new LinkedList<>(solution);
        newSolution.add(first.get(0));
        weave(new LinkedList<>(first.subList(1, first.size())), second, solutions, newSolution);

        newSolution = new LinkedList<>(solution);
        newSolution.add(second.get(0));
        weave(first, new LinkedList<>(second.subList(1, second.size())), solutions, newSolution);
    }

    private static class Node {
        private Node left, right;
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }

}